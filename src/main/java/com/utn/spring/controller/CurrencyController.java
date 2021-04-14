package com.utn.spring.controller;

import com.utn.spring.model.Currency;
import com.utn.spring.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Currency")
public class CurrencyController
{
    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public void addCurrency(@RequestBody Currency currency)
    {
        currencyService.addCurrency(currency);
    }

    @GetMapping("{/id}")
    public Currency getCurrencyById(@PathVariable Integer id)
    {
        return currencyService.getCurrencyById(id);
    }

    @GetMapping
    public List<Currency>getAll()
    {
        return currencyService.getAll();
    }

    @DeleteMapping("{/id}")
    public void deleteCurrency(@PathVariable Integer id)
    {
        currencyService.removeCurrency(id);
    }
}
