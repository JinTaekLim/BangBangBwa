package com.bangbangbwa.backend.global.util.randomValue;

public class RandomString {

  private RandomString() {
    throw new AssertionError("Utility class should not be instantiated");
  }

  public static RandomStringGenerator getGenerator() {
    return new RandomStringGenerator();
  }
}
