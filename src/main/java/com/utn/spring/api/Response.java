package com.utn.spring.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response
{
    List<CurrencyApi>currencyApiList;
}
