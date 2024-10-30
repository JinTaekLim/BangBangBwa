package com.bangbangbwa.backend.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bangbangbwa.backend.domain.member.business.NicknameGenerator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class NicknameGeneratorTest {

  @Test
  void generateRandomNumbers() {
    Random random = new Random();
    int nums = random.nextInt(100, 999);
    System.out.println("랜덤 숫자 : " + nums);
  }

  @Test
  void generateRandomLowers() {
    Random random = new Random();
    int count = 3;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append((char) (random.nextInt(26) + 97));
    }
    System.out.println("랜덤 소문자 : " + sb);
  }

  @Test
  void generateRandomUppers() {
    Random random = new Random();
    int count = 3;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append((char) (random.nextInt(26) + 65));
    }
    System.out.println("랜덤 대문자 : " + sb);
  }

  @Test
  void generateRandomNickname() {
    NicknameGenerator nicknameGenerator = new NicknameGenerator();
    String nickname = nicknameGenerator.generate();
    System.out.println("랜덤 닉네임 : " + nickname);
  }

  @Test
  void generateRandomNickname_무작위_확률() {
    NicknameGenerator nicknameGenerator = new NicknameGenerator();
    int total = 1000, allowableRange = 900, needNicknameCount = 10, hit = 0;
    for (int i = 0; i < total; i++) {
      Set<String> nicknames = new HashSet<>();
      for (int j = 0; j < needNicknameCount; j++) {
        nicknames.add(nicknameGenerator.generate());
      }
      if (nicknames.size() == needNicknameCount) {
        hit++;
      }
    }
    System.out.println("성공 확률 : " + hit / total * 100 + "%");
    assertTrue(allowableRange <= hit);
  }
}
