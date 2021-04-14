package com.utn.spring.controller;

import com.utn.spring.model.Persona;
import com.utn.spring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Persona")
public class PersonaController
{
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public void addPerson(@RequestBody Persona persona)
    {
        personaService.addPerson(persona);
    }

    @GetMapping("{/id}")
    public Persona getPersonById(@PathVariable Integer id)
    {
        return personaService.getPersonById(id);
    }

    @GetMapping
    public List<Persona>getAll()
    {
        return personaService.getAll();
    }

    @DeleteMapping("{/id}")
    public void deletePerson(@PathVariable Integer id)
    {
        personaService.deletePerson(id);
    }

    @PutMapping("/{id}/jugadres/{idJugador}")
    public void addJugador(@PathVariable Integer id, @PathVariable Integer idJugador)
    {
        personaService.addJugador(id,idJugador);
    }

    @PutMapping("/id/personas/idAmigo")
    public void putFriends(@PathVariable Integer id, @PathVariable Integer idFriends)
    {
        personaService.putFriends(id,idFriends);
    }
}
