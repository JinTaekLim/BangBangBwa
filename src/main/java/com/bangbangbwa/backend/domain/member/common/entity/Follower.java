package com.bangbangbwa.backend.domain.member.common.entity;

import lombok.Getter;

@Getter
public class Follower {
  private Long memberId;
  private Long followeeMemberId;
  private String name;
  private String imageUrl;
}
