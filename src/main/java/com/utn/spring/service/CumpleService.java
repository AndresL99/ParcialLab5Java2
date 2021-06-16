package com.utn.spring.service;

import com.utn.spring.api.ApiArgentinianPlayer;
import com.utn.spring.api.ApiCurrencyExhange;
import com.utn.spring.api.SportDataApi;
import com.utn.spring.model.*;
import com.utn.spring.model.dto.PersonDTO;
import com.utn.spring.repository.CumpleRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CumpleService
{
    @Autowired
    private CumpleRepository cumpleRepository;
    private PersonaService personaService;
    private ApiCurrencyExhange apiCurrencyExhange;
    private ApiArgentinianPlayer apiArgentinianPlayer;

    public CumpleService(CumpleRepository cumpleRepository, PersonaService personaService,ApiCurrencyExhange apiCurrencyExhange,ApiArgentinianPlayer apiArgentinianPlayer) {
        this.cumpleRepository = cumpleRepository;
        this.personaService = personaService;
        this.apiCurrencyExhange= apiCurrencyExhange;
        this.apiArgentinianPlayer = apiArgentinianPlayer;
    }

    public void addBirthday(Cumpleañitos cumpleañitos)
    {
        cumpleRepository.save(cumpleañitos);
    }

    public Cumpleañitos getBirthdayById(Integer id)
    {
        return cumpleRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addInvitadoCumple(Integer id,Integer idInvitado)
    {
        Cumpleañitos c = getBirthdayById(id);
        Persona p = personaService.getPersonById(idInvitado);
        c.getInvitados().add(p);
        cumpleRepository.save(c);
    }

        @SneakyThrows
        public Page<PersonDTO>getConversion(Pageable pageable)
        {
            Page<Cumpleañitos>birthdayPage = cumpleRepository.findAll(pageable);

            Double dolarExchange = apiCurrencyExhange.getExchangeCurrencyDolar();
            Double euroExchange = apiCurrencyExhange.getExchangeCurrencyEuro();

            List<PersonDTO>list = new ArrayList<>();

            for(Cumpleañitos cumpleañitos : birthdayPage.getContent())
            {
                for(Persona persona : cumpleañitos.getInvitados())
                {
                    if(persona instanceof Jugador)
                    {
                        PersonDTO personDTO =  new PersonDTO();
                        personDTO.setName(persona.getName()+ ""+ persona.getLastName());
                        Currency currency = ((Jugador)persona).getCurrency();
                        if(currency.getCurrency().equals(TypeCurrency.DOLAR))
                        {
                            personDTO.setAmount((float) (25000/(Double)dolarExchange));
                        }
                        else
                        {
                            personDTO.setAmount((float) (25000/euroExchange));
                        }
                    }
                }
            }

            Page<PersonDTO>personDTOPage = new PageImpl<>(list,pageable, list.size());
            return personDTOPage;
        }


        @SneakyThrows
        public Page<PersonDTO>getArgentinianIdol(Pageable pageable)
        {
            Page<Cumpleañitos>cumpleañitosPage = cumpleRepository.findAll(pageable);

            SportDataApi sportDataApi = apiArgentinianPlayer.getData();

            List<PersonDTO>peopleList = new ArrayList<>();

            for(Cumpleañitos cumpleañitos : cumpleañitosPage.getContent())
            {
                for(Persona persona : cumpleañitos.getInvitados())
                {
                    if(persona instanceof Jugador)
                    {
                        PersonDTO dto = new PersonDTO();
                        dto.setName(persona.getName()+""+persona.getLastName());
                        if((((Jugador) persona).getAge() < 20)&&(((Jugador) persona).getAltura() > 1.80))
                        {
                            Set<PersonDTO> guest = new HashSet<>();
                            dto.setGuest(guest);
                        }
                    }
                }
            }
            Page<PersonDTO>dtoPage = new PageImpl<>(peopleList,pageable,peopleList.size());
            return dtoPage;
        }

}
