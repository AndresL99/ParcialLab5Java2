package com.utn.spring.controller;

import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.model.Persona;
import com.utn.spring.model.dto.PersonDTO;
import com.utn.spring.service.CumpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Birthday")
public class CumpleController
{
    @Autowired
    private CumpleService cumpleService;

    public CumpleController(CumpleService cumpleService)
    {
        this.cumpleService = cumpleService;
    }

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

    @GetMapping("/payment")
    public ResponseEntity<List<PersonDTO>>payment(Pageable pageable)
    {
        Page<PersonDTO>personDTOPage = cumpleService.getConversion(pageable);
        return response(personDTOPage);
    }

    public static ResponseEntity response(Page page)
    {
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-Total-Content",String.valueOf(page.getTotalElements()))
                .body(page.getContent());
    }
}
