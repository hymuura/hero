package com.mindata.hero.repository;

import com.mindata.hero.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    @Query("SELECT h FROM Hero h WHERE LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Hero> findByNameContainingIgnoreCase(@Param("name") String name);
}
