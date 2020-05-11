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
    if (expLevelConfig.keySet().size() != 11) {
      throw new ConfigurationException("Level must be from 1 to 100 grouped by 10 levels");
    }
    for (var e : expLevelConfig.entrySet()) {
      if (e.getKey()!= 100 && e.getValue() <= 0) {
        throw new ConfigurationException("Experience levelup must be greater than 0");
      }
    }
    for (int i = 1; i <= 100; i++) {
      Integer configLevel = i < 10 ? 1 : i / 10 * 10;
      if (!expLevelConfig.containsKey(configLevel)) {
        throw new ConfigurationException(
            String.format("Level : '%s' must be set in config file", i));
      }
      expLevelConfigResult.put(i, expLevelConfig.get(configLevel));
    }
    return expLevelConfigResult;
  }

  public Map<Integer, Integer> getExpLevelConfig() {
    return this.expLevelConfig;
  }

}
