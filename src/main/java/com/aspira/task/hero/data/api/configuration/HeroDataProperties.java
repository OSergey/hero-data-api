package com.aspira.task.hero.data.api.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "hero.config")
@Getter
@Setter
@Validated
public class HeroDataProperties {

  @NotNull
  @Valid
  private Resource expLevelConfig;

}