package com.utn.spring.service;

import com.utn.spring.model.Amigo;
import com.utn.spring.model.Jugador;
import com.utn.spring.model.Persona;
import com.utn.spring.model.Representante;
import com.utn.spring.repository.PersonaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PersonaService
{
    private PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void addPerson(Persona p)
    {
        personaRepository.save(p);
    }

    public Persona getPersonById(Integer id)
    {
        return personaRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Persona>getAll()
    {
        return personaRepository.findAll();
    }

    public void addJugador(Integer id, Integer idJugador)
    {
        Representante r = (Representante) getPersonById(id);
        Jugador j = (Jugador) getPersonById(idJugador);
        r.getJugadores().add(j);
        r.setMontoTotal();
        personaRepository.save(r);
    }

    public void putFriends(Integer id, Integer idFriends)
    {
        Representante r = (Representante) getPersonById(id);
        Amigo amigo = (Amigo) getPersonById(idFriends);
        r.getAmigos().add(amigo);
        personaRepository.save(r);
    }

    public void deletePerson(Integer id)
    {
        personaRepository.deleteById(id);
    }
}
