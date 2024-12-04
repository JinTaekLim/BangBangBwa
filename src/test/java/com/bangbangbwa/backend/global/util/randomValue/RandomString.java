package com.bangbangbwa.backend.global.util.randomValue;

public class RandomString {

  private static RandomStringGenerator generator;

  private RandomString() {
    throw new AssertionError("Utility class should not be instantiated");
  }

  public static RandomStringGenerator getGenerator() {
    if (generator == null) {
      generator = new RandomStringGenerator();
    }
    return generator;
  }
}
