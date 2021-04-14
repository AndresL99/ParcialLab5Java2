package com.utn.spring.controller;

import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.service.CumpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Birthday")
public class CumpleController
{
    @Autowired
    private CumpleService cumpleService;

    @PostMapping
    public void addBirthday(@RequestBody Cumpleañitos cumpleañitos)
    {
        cumpleService.addBirthday(cumpleañitos);
    }

    @GetMapping("/{id}")
    public Cumpleañitos getBirthdayById(@PathVariable Integer id)
    {
        return cumpleService.getBirthdayById(id);
    }

    @PutMapping("/id/personas/idPersona")
    public void addInvitadoCumple(@PathVariable Integer id,@PathVariable Integer idPersona)
    {
        cumpleService.addInvitadoCumple(id,idPersona);
    }
}
