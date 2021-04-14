package com.utn.spring.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "typePerson",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name = "Jugador"),
        @JsonSubTypes.Type(value = Representante.class, name = "Representante"),
        @JsonSubTypes.Type(value = Amigo.class, name = "Amigo")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Persona
{
    @Id
    private Integer id;
    @NotNull(message = "Campo nombre obligatorio.")
    private String name;
    @NotNull(message = "Campo apellido obligatorio.")
    private String lastName;

    @OneToMany
    private Set<Cumpleañitos>cumpleañitos;



    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePerson typePerson();
}
