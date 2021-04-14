package com.utn.spring.controller;


import com.utn.spring.model.dto.PersonDTO;
import com.utn.spring.service.CumpleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static com.utn.spring.utils.Utils.aBirthday;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CumpleControllerTest
{
    private CumpleService cumpleService;
    private CumpleController cumpleController;

    @BeforeEach
    public void setUp()
    {
        cumpleService = mock(CumpleService.class);
        cumpleController = new CumpleController(cumpleService);
    }

    @Test
    public void addCumpleOk()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        //when(cumpleService.addBirthday(aBirthday())).thenReturn(aBirthday());


    }

    @Test
    public void getGuestsConversionOKTest()
    {
        when(cumpleService.getConversion(any())).thenReturn(aPersonConversionList());

        ResponseEntity<List<PersonDTO>> response = cumpleController.payment(aPageable());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(aPersonConversionList().get(0).getAmount(), response.getBody().get(0).getAmount());
    }



    private static Page<PersonDTO> aPersonConversionList()
    {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAmount(50F);


        Page<PersonDTO>dto = new PageImpl(List.of(personDTO));
        return dto;
    }

    public static Pageable aPageable(){
        return PageRequest.of(0,10);
    }
}
