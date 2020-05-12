package com.aspira.task.hero.data.api.service;

import com.aspira.task.hero.data.api.exception.HeroDataException;
import com.aspira.task.hero.data.api.model.Hero;
import java.util.List;

public interface HeroDataService {

  Hero addExperience(Integer id, Integer experience);

  Hero getHeroById(Integer id);

  List<Hero> getHeroes();

  Hero addHero(Integer id) throws HeroDataException;
}
