package com.utn.spring.service;

import com.utn.spring.api.ApiCurrencyExhange;
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
import java.util.List;

@Service
public class CumpleService
{
    @Autowired
    private CumpleRepository cumpleRepository;
    private PersonaService personaService;
    private ApiCurrencyExhange apiCurrencyExhange;

    public CumpleService(CumpleRepository cumpleRepository, PersonaService personaService,ApiCurrencyExhange apiCurrencyExhange) {
        this.cumpleRepository = cumpleRepository;
        this.personaService = personaService;
        this.apiCurrencyExhange= apiCurrencyExhange;
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

}
