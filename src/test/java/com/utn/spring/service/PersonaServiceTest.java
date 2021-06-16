package com.utn.spring.service;

import com.utn.spring.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonaServiceTest
{
    @InjectMocks
    PersonaService personaService;

    @Mock
    PersonaRepository personaRepository;

    @BeforeEach
    public void setUp()
    {
        personaRepository = mock(PersonaRepository.class);
        personaService = new PersonaService(personaRepository);
    }


}
