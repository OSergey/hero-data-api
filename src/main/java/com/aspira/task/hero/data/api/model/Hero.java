package com.aspira.task.hero.data.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hero {

  private Integer id;
  private Integer level;
  private Integer experience;
}
