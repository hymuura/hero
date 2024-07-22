package com.mindata.hero.service;

import com.mindata.hero.dto.HeroDto;

import java.util.List;

public interface HeroService {
    HeroDto createHero(HeroDto hero);
    HeroDto updateHero(HeroDto hero, Long heroId);
    void deleteHero(Long heroId);
    HeroDto findHeroById(Long heroId);
    List<HeroDto> findAllByCriteria(String name);
}
