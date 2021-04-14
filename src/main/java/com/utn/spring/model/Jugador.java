package com.utn.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Jugador extends Persona
{
    private Float peso;
    private Integer altura;
    private Integer goles;
    private Integer minutosJugados;
    private Date fechaNacimiento;
    @OneToOne
    private Currency currency;

    @Override
    public TypePerson typePerson() {
        return TypePerson.JUGADOR;
    }
}
