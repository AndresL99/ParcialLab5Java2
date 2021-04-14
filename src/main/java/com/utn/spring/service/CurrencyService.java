package com.utn.spring.service;

import com.utn.spring.model.Currency;
import com.utn.spring.repository.CurrencyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CurrencyService
{
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void addCurrency(Currency currency)
    {
        currencyRepository.save(currency);
    }

    public Currency getCurrencyById(Integer id)
    {
        return currencyRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Currency>getAll()
    {
        return currencyRepository.findAll();
    }

    public void removeCurrency(Integer id)
    {
        currencyRepository.deleteById(id);
    }
}
