package com.utn.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Representante extends Persona
{
    private Float pesoDeLaBoveda;
    private Float montoTotal;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jugador")
    private List<Jugador>jugadores;

    @OneToMany()
    private List<Amigo>amigos;

    @Override
    public TypePerson typePerson() {
        return TypePerson.REPRESENTANTE;
    }


    public void setMontoTotal() {
        float acumulador = 0;
        for(Jugador j : jugadores)
        {
            acumulador += j.getCurrency().getMonto()+ j.getCurrency().typeCurrency().getCotizacion();
        }
        this.montoTotal = acumulador;
        this.pesoDeLaBoveda = acumulador/1000;
    }
}
