package com.bangbangbwa.backend.domain.oauth.common.dto;

public record NaverInfoDto(
    Response response
) {

  public record Response(
      String id,
      String email
  ) {

  }
}
