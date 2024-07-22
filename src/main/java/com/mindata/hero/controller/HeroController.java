package com.mindata.hero.controller;

import com.mindata.hero.dto.HeroDto;
import com.mindata.hero.service.HeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private final HeroService service;

    public HeroController(HeroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HeroDto>> findAllByCriteria(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(service.findAllByCriteria(name));
    }

    @GetMapping("/{heroId}")
    public ResponseEntity<HeroDto> findById(@PathVariable Long heroId) {
        return ResponseEntity.ok(service.findHeroById(heroId));
    }

    @PostMapping
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroDto hero) {
        return ResponseEntity.ok(service.createHero(hero));
    }

    @PutMapping("/{heroId}")
    public ResponseEntity<HeroDto> updateHero(@RequestBody HeroDto hero, @PathVariable Long heroId) {
        return ResponseEntity.ok(service.updateHero(hero, heroId));
    }

    @DeleteMapping("/{heroId}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long heroId) {
        service.deleteHero(heroId);
        return ResponseEntity.noContent().build();
    }
}
