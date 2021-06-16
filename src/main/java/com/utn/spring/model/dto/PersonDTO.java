package com.utn.spring.model.dto;

import com.utn.spring.model.Amigo;
import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.model.TypeCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDTO
{
    private String name;
    private Float amount;
    private TypeCurrency typeCurrency;
    private String age;
    private Double height;
    private Set<PersonDTO>guest;
}
