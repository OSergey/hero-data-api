package com.aspira.task.hero.data.api.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HeroDataException extends Exception {

  public HeroDataException(String message) {
    super(message);
  }
}
