package com.bangbangbwa.backend.domain.member.common.dto;

public class FollowerDto {
  public record Response(
    Long memberId,
    String name,
    String imageUrl
  ) {
  }
}
