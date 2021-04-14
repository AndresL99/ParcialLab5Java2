package com.utn.spring.model;

public enum TypePerson
{
    REPRESENTANTE("Representante"),
    JUGADOR("Jugador"),
    AMIGO("Amigo");

    private String descryption;

    TypePerson(String descryption) {
        this.descryption = descryption;
    }

    public String getDescryption() {
        return descryption;
    }

    public TypePerson find(final String value)
    {
        for(TypePerson t : values())
        {
            if(t.toString().equalsIgnoreCase(value))
            {
                return t;
            }
        }
            throw new IllegalArgumentException(String.format("Persona Incorrecta %s",value));
    }
}
