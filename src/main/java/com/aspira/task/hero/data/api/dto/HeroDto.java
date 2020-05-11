package com.aspira.task.hero.data.api.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroDto implements Serializable {

  private Integer id;
  private Integer level;
  private Integer experience;
}
