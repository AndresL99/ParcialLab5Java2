package com.utn.spring.model;

public enum TypeCurrency
{
    DOLAR("Dolar",144),
    EURO("Euro",112);

    private String descryption;
    private Integer cotizacion;

    TypeCurrency(String descryption, Integer cotizacion) {
        this.descryption = descryption;
        this.cotizacion = cotizacion;
    }

    public Integer getCotizacion() {
        return cotizacion;
    }

    public String getDescryption() {
        return descryption;
    }

    public TypeCurrency find(final String value)
    {
        for(TypeCurrency t : values())
        {
            if(t.toString().equalsIgnoreCase(value))
            {
                return t;
            }
        }
        throw new IllegalArgumentException(String.format("Incorrect Currency  %s",value));
    }
}
