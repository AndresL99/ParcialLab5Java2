package com.utn.spring.service;

import com.utn.spring.api.ApiArgentinianPlayer;
import com.utn.spring.api.ApiCurrencyExhange;
import com.utn.spring.model.Cumpleañitos;
import com.utn.spring.model.dto.PersonDTO;
import com.utn.spring.repository.CumpleRepository;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.utn.spring.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CumpleServiceTest
{
    @InjectMocks
    private CumpleService cumpleService;

    @Mock
    private CumpleRepository cumpleRepository;
    private ApiArgentinianPlayer apiArgentinianPlayer;
    private ApiCurrencyExhange apiCurrencyExhange;
    private PersonaService personaService;

    @BeforeEach
    public void setUp()
    {
        cumpleRepository = mock(CumpleRepository.class);
        personaService = mock(PersonaService.class);
        apiArgentinianPlayer = mock(ApiArgentinianPlayer.class);
        apiCurrencyExhange = mock(ApiCurrencyExhange.class);
        cumpleService = new CumpleService(cumpleRepository,personaService,apiCurrencyExhange,apiArgentinianPlayer);
    }

    /*@Test
    public void getArgentinianIdolOk()
    {
        when(cumpleRepository.findAll(any(Pageable.class))).thenReturn(aPlayerIdol());

        Page<PersonDTO>personDTOPage = cumpleService.getArgentinianIdol(aPageable());

        assertEquals(aPlayerIdol().getTotalElements(), personDTOPage.getTotalElements());
        //assertEquals(aPlayerIdol().getContent().get(0).getInvitados(), personDTOPage.getContent().get(0).getGuest());

        verify(cumpleRepository,times(1)).findAll(aPageable());

    }*/

    @Test
    public void getByIdTestOk()
    {
        Integer id = 1;

        when(cumpleRepository.findById(1)).thenReturn(java.util.Optional.of(aCumple()));

        Cumpleañitos cumpleañitos = cumpleService.getBirthdayById(1);

        verify(cumpleRepository, times(1)).findById(1);
    }

    @Test
    public void getByIdWithException()
    {
        Integer id = 2;

        when(cumpleRepository.findById(2)).thenReturn(java.util.Optional.of(aCumple()));

        cumpleService.getBirthdayById(2);

        verify(cumpleRepository,times(1)).findById(2);
    }

}
