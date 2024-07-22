package com.mindata.hero.service;

import com.mindata.hero.dto.HeroDto;
import com.mindata.hero.entity.Hero;
import com.mindata.hero.exception.HeroNotFoundException;
import com.mindata.hero.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeroServiceImplTest {
    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroServiceImpl heroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createHero_ok() {
        // Given
        String heroName = "Batman";
        int heroAge = 40;
        Date creationDate = new Date();

        Hero hero = Hero.builder()
                .name(heroName)
                .age(heroAge)
                .creationDate(creationDate)
                .build();

        HeroDto heroDto = HeroDto.builder()
                .name(heroName)
                .age(heroAge)
                .build();

        when(heroRepository.save(any(Hero.class))).thenReturn(hero);

        // When
        HeroDto createdHero = heroService.createHero(heroDto);

        // Then
        assertNotNull(createdHero);
        assertEquals(heroName, createdHero.getName());
        verify(heroRepository, times(1)).save(any(Hero.class));
    }

    @Test
    void findHeroById_not_found() {
        // Given
        Long heroId = 1L;

        when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(HeroNotFoundException.class, () -> {
            heroService.findHeroById(heroId);
        });

        // Then
        assertEquals("Hero not found", exception.getMessage());
        verify(heroRepository, times(1)).findById(heroId);
    }
}