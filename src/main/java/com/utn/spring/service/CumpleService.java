package com.utn.spring.service;

import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.model.Persona;
import com.utn.spring.repository.CumpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CumpleService
{
    @Autowired
    private CumpleRepository cumpleRepository;
    private PersonaService personaService;

    public CumpleService(CumpleRepository cumpleRepository, PersonaService personaService) {
        this.cumpleRepository = cumpleRepository;
        this.personaService = personaService;
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

}
