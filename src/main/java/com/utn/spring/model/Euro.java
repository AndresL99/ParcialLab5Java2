package com.utn.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Euro extends Currency
{

    @Override
    public TypeCurrency typeCurrency() {
        return TypeCurrency.EURO;
    }
}
