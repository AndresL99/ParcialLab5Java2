package com.utn.spring.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "typeCurrency",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Euro.class, name = "Euro"),
        @JsonSubTypes.Type(value = Dolar.class, name = "Dolar")
})
public abstract class Currency
{
    @Id
    private Integer id;
    private String currency;
    private Integer monto;


    @org.springframework.data.annotation.AccessType(org.springframework.data.annotation.AccessType.Type.PROPERTY)
    public abstract TypeCurrency typeCurrency();
}
