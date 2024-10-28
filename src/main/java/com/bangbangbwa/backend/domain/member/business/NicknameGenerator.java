package com.bangbangbwa.backend.domain.member.business;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class NicknameGenerator {

  private final Random random = new Random();

  public String generate() {
    return randomLowers(3) + randomSpecial("_-") + randomUppers(3) + "(" + randomNums() + ")";
  }

  private String randomNums() {
    return String.valueOf(random.nextInt(100, 999));
  }

  private String randomLowers(final int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append((char) (random.nextInt(26) + 97));
    }
    return sb.toString();
  }

  private String randomUppers(final int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append((char) (random.nextInt(26) + 65));
    }
    return sb.toString();
  }

  private String randomSpecial(final String special) {
    return String.valueOf(special.charAt(random.nextInt(special.length())));
  }
}