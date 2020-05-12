package com.aspira.task.hero.data.api.service.impl;

import com.aspira.task.hero.data.api.service.ExpLevelConfigService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.naming.ConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

@RequiredArgsConstructor
public class ExpLevelConfigServiceImpl implements ExpLevelConfigService {

  private final Resource expLevelConfigFile;
  private final ObjectMapper objectMapper;
  private Map<Integer, Integer> expLevelConfig;


  @PostConstruct
  private void initExpLevelConfig() throws IOException, ConfigurationException {
    Map<Integer, Integer> expLevelConfig = objectMapper
        .readValue(expLevelConfigFile.getInputStream(), new TypeReference<>() {
        });
    this.expLevelConfig = validateConfig(expLevelConfig);

  }

  private Map<Integer, Integer> validateConfig(Map<Integer, Integer> expLevelConfig)
      throws ConfigurationException {
    Map<Integer, Integer> expLevelConfigResult = new HashMap<>();
    int maxLevel = expLevelConfig.keySet().stream().max(Integer::compareTo)
        .orElseThrow(() -> new ConfigurationException("Config file is empty"));
    int exp = 0;
    for (int level = 1; level <= maxLevel; level++) {
      if (expLevelConfig.containsKey(level)) {
        exp = expLevelConfig.get(level);
        if (expLevelConfig.get(level) <= 0 && level != maxLevel) {
          throw new ConfigurationException("Experience levelup must be greater than 0");
        }
      }
      expLevelConfigResult.put(level, exp);
    }
    return expLevelConfigResult;
  }

  public Map<Integer, Integer> getExpLevelConfig() {
    return this.expLevelConfig;
  }

}
