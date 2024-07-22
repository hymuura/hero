package com.mindata.hero.service;

import com.mindata.hero.dto.HeroDto;
import com.mindata.hero.entity.Hero;
import com.mindata.hero.exception.HeroNotFoundException;
import com.mindata.hero.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository repository;

    public HeroServiceImpl(HeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public HeroDto createHero(HeroDto hero) {
        Hero entity = mapRequestDtoToEntity.apply(hero);
        return mapEntityToResponseDto.apply(
                repository.save(entity)
        );
    }

    @Override
    public HeroDto updateHero(HeroDto hero, Long heroId) {
        Hero entity = repository.findById(heroId).orElseThrow(HeroNotFoundException::new);
        entity.setName(hero.getName());
        entity.setAge(hero.getAge());
        entity.setUpdateDate(new Date());
        return mapEntityToResponseDto.apply(
                repository.save(entity)
        );
    }

    @Override
    public void deleteHero(Long heroId) {
        Hero entity = repository.findById(heroId).orElseThrow(HeroNotFoundException::new);
        repository.delete(entity);
    }

    @Override
    public HeroDto findHeroById(Long heroId) {
        Hero entity = repository.findById(heroId).orElseThrow(HeroNotFoundException::new);
        return mapEntityToResponseDto.apply(entity);
    }

    @Override
    public List<HeroDto> findAllByCriteria(String name) {
        if (name == null || name.isEmpty()) {
            return repository.findAll().stream()
                    .map(mapEntityToResponseDto)
                    .collect(Collectors.toList());
        }

        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    private final Function<HeroDto, Hero> mapRequestDtoToEntity = dto -> Hero.builder()
            .name(dto.getName())
            .age(dto.getAge())
            .creationDate(new Date())
            .build();

    private final Function<Hero, HeroDto> mapEntityToResponseDto = entity -> HeroDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .age(entity.getAge())
            .build();
}
