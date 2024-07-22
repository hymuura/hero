package com.mindata.hero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindata.hero.dto.HeroDto;
import com.mindata.hero.entity.Hero;
import com.mindata.hero.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        heroRepository.deleteAll();
        Hero hero = new Hero();
        hero.setName("Test Hero");
        heroRepository.save(hero);
    }

    @Test
    void findById_shouldReturnHero() throws Exception {
        // Given
        Hero hero = heroRepository.findAll().get(0);
        Long heroId = hero.getId();

        // When & Then
        HeroDto actualHero = objectMapper.convertValue(hero, HeroDto.class);
        String actualHeroJson = objectMapper.writeValueAsString(actualHero);

        mockMvc.perform(get("/hero/{heroId}", heroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(actualHeroJson));
    }
}
