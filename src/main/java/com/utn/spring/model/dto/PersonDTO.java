package com.utn.spring.model.dto;

import com.utn.spring.model.TypeCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDTO
{
    private String name;
    private Float amount;
    private TypeCurrency typeCurrency;
}
