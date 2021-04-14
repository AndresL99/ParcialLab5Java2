package com.utn.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Amigo extends Persona
{

    private String profesion;
    private String statusSocial;

    @Override
    public TypePerson typePerson() {
        return TypePerson.AMIGO;
    }
}
