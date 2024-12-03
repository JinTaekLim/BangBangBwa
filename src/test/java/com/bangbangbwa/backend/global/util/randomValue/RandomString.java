package com.bangbangbwa.backend.global.util.randomValue;

import lombok.Getter;

public class RandomString {

  @Getter
  private static final RandomStringGenerator generator = new RandomStringGenerator();

}
