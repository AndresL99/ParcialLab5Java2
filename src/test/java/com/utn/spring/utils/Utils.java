package com.utn.spring.utils;

import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.model.Persona;
import com.utn.spring.model.TypeCurrency;
import com.utn.spring.model.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils
{
    private static Page<PersonDTO> aPersonConversionList()
    {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAmount(50F);


        Page<PersonDTO>dto = new PageImpl(List.of(personDTO));
        return dto;
    }

    public static Pageable aPageable(){
        return PageRequest.of(0,10);
    }

    public static PersonDTO aPersona()
    {
        return PersonDTO.builder().name("Leo").typeCurrency(TypeCurrency.EURO).amount(200F).build();
    }


    public static void aBirthday()
    {
        Set<Persona>guest = null;
        new Cumpleañitos(3, LocalDate.of(2021,7,2),aPersona(),guest);
    }

    public static Cumpleañitos aCumple()
    {
        return new Cumpleañitos(1,LocalDate.now(),aPersona(),new HashSet<>());
    }


    /*public static Page<Cumpleañitos> aPlayerIdol()
    {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge("20");
        personDTO.setHeight(1.80);

        Page<PersonDTO>page = new PageImpl<>(List.of(personDTO));
        return page;
    }*/




}
