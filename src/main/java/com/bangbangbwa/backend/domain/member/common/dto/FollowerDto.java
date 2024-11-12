package com.bangbangbwa.backend.domain.member.common.dto;

import lombok.Getter;

@Getter
public class FollowerDto {
  private Long followerMemberId;
  private Long memberId;
  private String followerName;
  private String followerImageUrl;
}
