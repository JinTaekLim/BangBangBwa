package com.bangbangbwa.backend.domain.member.common.dto;

import lombok.Getter;

@Getter
public class SummaryDto {
  private Long memberId;
  private Long followerCount;
  private Long followingCount;
  private Long postCount;
}
