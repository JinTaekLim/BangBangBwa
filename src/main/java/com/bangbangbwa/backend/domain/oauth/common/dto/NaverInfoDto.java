package com.bangbangbwa.backend.domain.oauth.common.dto;

public record NaverInfoDto(String resultcode, String message, Response response) {

  public record Response(
      String id,
      String email,
      String nickname,
      String profile_image,
      String age,
      String gender,
      String name,
      String birthday,
      String birthyear,
      String mobile
  ) {

  }
}
