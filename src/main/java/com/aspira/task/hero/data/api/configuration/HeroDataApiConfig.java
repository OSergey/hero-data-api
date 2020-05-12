package com.aspira.task.hero.data.api.configuration;

import com.aspira.task.hero.data.api.service.ExpLevelConfigService;
import com.aspira.task.hero.data.api.service.HeroDataService;
import com.aspira.task.hero.data.api.service.impl.ExpLevelConfigServiceImpl;
import com.aspira.task.hero.data.api.service.impl.HeroDataServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroDataApiConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public ExpLevelConfigService expLevelConfig(ObjectMapper objectMapper, HeroDataProperties heroDataProperties) {
    return new ExpLevelConfigServiceImpl(heroDataProperties.getExpLevelConfig(), objectMapper);
  }

  @Bean
  public HeroDataService heroDataService(ExpLevelConfigService expLevelConfig, HeroDataProperties heroDataProperties) {
    return new HeroDataServiceImpl(expLevelConfig, heroDataProperties);
  }
}
