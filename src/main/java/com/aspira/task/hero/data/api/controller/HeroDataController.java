package com.aspira.task.hero.data.api.controller;

import com.aspira.task.hero.data.api.dto.HeroDto;
import com.aspira.task.hero.data.api.exception.HeroDataException;
import com.aspira.task.hero.data.api.model.Hero;
import com.aspira.task.hero.data.api.service.HeroDataService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/hero-management/heroes")
public class HeroDataController {

  @Autowired
  private HeroDataService heroDataService;


  @PostMapping
  public HeroDto add(@RequestBody Integer id) throws HeroDataException {
    Hero hero = heroDataService.addHero(id);
    return HeroDto.builder().id(hero.getId()).level(hero.getLevel())
        .experience(hero.getExperience()).build();
  }

  @GetMapping
  public List<HeroDto> getAll() {
    return heroDataService.getHeroes().stream()
        .map(hero -> HeroDto.builder().id(hero.getId()).level(hero.getLevel())
            .experience(hero.getExperience()).build())
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/{heroId}")
  public HeroDto get(@PathVariable Integer heroId) throws HeroDataException {
    log.debug("Hero id: {}", heroId);
    Hero hero = heroDataService.getHeroById(heroId);
    if (hero == null) {
      throw new HeroDataException(String.format("Hero with id: '%s' not found", heroId));
    }
    return HeroDto.builder().id(hero.getId()).level(hero.getLevel())
        .experience(hero.getExperience()).build();
  }

  @PutMapping(value = "/{heroId}/experience")
  public HeroDto updateExperience(@PathVariable Integer heroId, @RequestBody Integer experience)
      throws HeroDataException {
    log.debug("Hero id: {}, experience: {}", heroId, experience);
    Hero hero = heroDataService.addExperience(heroId, experience);
    if (hero == null) {
      throw new HeroDataException(String.format("Hero with id: '%s' not found", heroId));
    }
    return HeroDto.builder().id(hero.getId()).level(hero.getLevel())
        .experience(hero.getExperience()).build();
  }
}
