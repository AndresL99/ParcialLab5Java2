package com.utn.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Cumpleañitos
{
    @Id
    private Integer idCumple;
    private LocalDate fecha;
    @OneToOne
    private Persona cumpleaniero;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona")
    private Set<Persona> invitados;

    public void setInvitados(Set<Persona> invitados)
    {
        if(invitados.size() < 10)
        {
            this.invitados = invitados;
        }
        else
        {
            Set<Persona>invitados2 = new HashSet<>();
            this.invitados = invitados2;
        }
    }
}
