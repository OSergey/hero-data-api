package com.aspira.task.hero.data.api.service.impl;

import com.aspira.task.hero.data.api.exception.HeroDataException;
import com.aspira.task.hero.data.api.model.Hero;
import com.aspira.task.hero.data.api.service.ExpLevelConfigService;
import com.aspira.task.hero.data.api.service.HeroDataService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeroDataServiceImpl implements HeroDataService {

  private final ExpLevelConfigService expLevelConfigService;
  private Map<Integer, Hero> cache = new ConcurrentHashMap();

  @Override
  public Hero addExperience(Integer id, Integer experience) {
    return cache.computeIfPresent(id, (integer, hero) -> {
      Integer expLevelUp;
      hero.setExperience(hero.getExperience() + experience);
      do {
        expLevelUp = expLevelConfigService.getExpLevelConfig().get(hero.getLevel());
        if (hero.getExperience() >= expLevelUp) {
          hero.setLevel(hero.getLevel() + 1);
          hero.setExperience(hero.getExperience() - expLevelUp);
        }
      } while (hero.getExperience() >= expLevelUp || hero.getLevel() == 100);
      return hero;
    });
  }

  @Override
  public Hero getHeroById(Integer id) {
    return cache.get(id);
  }

  @Override
  public List<Hero> getHeroes() {
    return cache.values().stream().collect(Collectors.toList());
  }

  @Override
  public Hero addHero(Integer id) throws HeroDataException {
    if(cache.containsKey(id)){
      throw new HeroDataException(String.format("Hero with id: '%s', already exist", id));
    }
    Hero hero = Hero.builder().id(id).experience(0).level(1).build();
    cache.put(id, hero);
    return hero;
  }
}
