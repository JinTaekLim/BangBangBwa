package com.bangbangbwa.backend.global.util.randomValue;

import java.security.SecureRandom;

/**
 * 랜덤 문자열을 생성하는 유틸리티 클래스입니다.
 * 빌더 패턴을 사용하여 생성할 문자열의 특성을 설정할 수 있습니다.
 * 대문자, 소문자, 숫자, 특수문자를 포함할지 여부와 문자열의 길이 범위를 지정할 수 있습니다.
 */
public class RandomStringGenerator {

  /** 사용 가능한 대문자 문자열 */
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  /** 사용 가능한 소문자 문자열 */
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  /** 사용 가능한 숫자 문자열 */
  private static final String NUMBERS = "0123456789";
  /** 사용 가능한 특수문자 문자열 */
  private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+";

  /** 대문자 포함 여부 (기본값: true) */
  private boolean includeUppercase = true;
  /** 소문자 포함 여부 (기본값: true) */
  private boolean includeLowercase = true;
  /** 숫자 포함 여부 (기본값: true) */
  private boolean includeNumbers = true;
  /** 특수문자 포함 여부 (기본값: true) */
  private boolean includeSpecialChars = true;

  /** null 값 허용 여부 (기본값: true) */
  private boolean nullable = true;
  /** 최소 길이 (기본값: 0) */
  private int minLength = 0;
  /** 최대 길이 (기본값: 20) */
  private int maxLength = 20;

  /** 난수 생성기 */
  private final SecureRandom random;

  /**
   * 기본 생성자입니다.
   * SecureRandom 인스턴스를 새로 생성하여 사용합니다.
   */
  public RandomStringGenerator() {
    this(new SecureRandom());
  }

  /**
   * SecureRandom 인스턴스를 지정하여 생성하는 생성자입니다.
   *
   * @param random 사용할 SecureRandom 인스턴스
   */
  public RandomStringGenerator(SecureRandom random) {
    this.random = random;
  }

  /**
   * 대문자 포함 여부를 설정합니다.
   *
   * @param include 대문자 포함 여부
   * @return 현재 RandomStringGenerator 인스턴스
   */
  public RandomStringGenerator withUppercase(boolean include) {
    this.includeUppercase = include;
    return this;
  }

  /**
   * 소문자 포함 여부를 설정합니다.
   *
   * @param include 소문자 포함 여부
   * @return 현재 RandomStringGenerator 인스턴스
   */
  public RandomStringGenerator withLowercase(boolean include) {
    this.includeLowercase = include;
    return this;
  }

  /**
   * 숫자 포함 여부를 설정합니다.
   *
   * @param include 숫자 포함 여부
   * @return 현재 RandomStringGenerator 인스턴스
   */
  public RandomStringGenerator withNumbers(boolean include) {
    this.includeNumbers = include;
    return this;
  }

  /**
   * 특수문자 포함 여부를 설정합니다.
   *
   * @param include 특수문자 포함 여부
   * @return 현재 RandomStringGenerator 인스턴스
   */
  public RandomStringGenerator withSpecialChars(boolean include) {
    this.includeSpecialChars = include;
    return this;
  }

  /**
   * null 값 허용 여부를 설정합니다.
   *
   * @param nullable null 값 허용 여부
   * @return 현재 RandomStringGenerator 인스턴스
   */
  public RandomStringGenerator nullable(boolean nullable) {
    this.nullable = nullable;
    return this;
  }

  /**
   * 생성될 문자열의 최소 길이를 설정합니다.
   *
   * @param minLength 최소 길이 (0 이상이어야 함)
   * @return 현재 RandomStringGenerator 인스턴스
   * @throws IllegalArgumentException minLength가 0보다 작은 경우
   */
  public RandomStringGenerator minLength(int minLength) {
    if (minLength < 0) {
      throw new IllegalArgumentException("minLength must be non-negative");
    }
    this.minLength = minLength;
    return this;
  }

  /**
   * 생성될 문자열의 최대 길이를 설정합니다.
   *
   * @param maxLength 최대 길이 (0 이상이어야 함)
   * @return 현재 RandomStringGenerator 인스턴스
   * @throws IllegalArgumentException maxLength가 0보다 작은 경우
   */
  public RandomStringGenerator maxLength(int maxLength) {
    if (maxLength < 0) {
      throw new IllegalArgumentException("maxLength must be non-negative");
    }
    this.maxLength = maxLength;
    return this;
  }

  /**
   * 설정된 조건에 따라 랜덤 문자열을 생성합니다.
   * nullable이 true이고 랜덤하게 선택된 경우 null을 반환할 수 있습니다.
   *
   * @return 생성된 랜덤 문자열 또는 null
   * @throws IllegalStateException minLength가 maxLength보다 큰 경우
   * @throws IllegalStateException 사용 가능한 문자 유형이 하나도 없는 경우
   */
  public String generate() {
    // 먼저 모든 유효성 검증을 수행
    validateGeneration();

    if (nullable && random.nextBoolean()) {
      return null;
    }
    return createRandomString();
  }

  /**
   * 문자열 생성에 필요한 모든 조건을 검증하는 내부 메소드입니다.
   *
   * @throws IllegalStateException minLength가 maxLength보다 큰 경우
   * @throws IllegalStateException 사용 가능한 문자 유형이 하나도 없는 경우
   */
  private void validateGeneration() {
    // 길이 범위 검증
    if (minLength > maxLength) {
      throw new IllegalStateException("minLength (" + minLength + ") cannot be greater than maxLength (" + maxLength + ")");
    }

    // 사용 가능한 문자 검증
    if (getAvailableCharacters().isEmpty()) {
      throw new IllegalStateException("At least one character type must be included");
    }
  }

  /**
   * 설정된 조건에 따라 실제 랜덤 문자열을 생성하는 내부 메소드입니다.
   * minLength와 maxLength 사이의 랜덤한 길이로 문자열을 생성하며,
   * 설정된 문자 유형(대문자, 소문자, 숫자, 특수문자)들 중에서 랜덤하게 문자를 선택합니다.
   * 이 메소드는 validateGeneration()을 통과한 후에만 호출되어야 합니다.
   *
   * @return 생성된 랜덤 문자열
   */
  private String createRandomString() {
    int length = random.nextInt(minLength, maxLength + 1);
    String availableChars = getAvailableCharacters();
    int charsLength = availableChars.length();

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(charsLength);
      char c = availableChars.charAt(index);
      sb.append(c);
    }

    return sb.toString();
  }


  /**
   * 설정된 조건에 따라 사용 가능한 문자들을 모아서 반환하는 내부 메소드입니다.
   *
   * @return 사용 가능한 문자들을 모은 문자열
   */
  private String getAvailableCharacters() {
    StringBuilder chars = new StringBuilder();
    if (includeUppercase) chars.append(UPPERCASE);
    if (includeLowercase) chars.append(LOWERCASE);
    if (includeNumbers) chars.append(NUMBERS);
    if (includeSpecialChars) chars.append(SPECIAL_CHARS);
    return chars.toString();
  }
}
