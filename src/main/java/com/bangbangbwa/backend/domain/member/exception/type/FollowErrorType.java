package com.bangbangbwa.backend.domain.member.exception.type;

import lombok.Getter;

@Getter
public enum FollowErrorType {
  NOT_FOUND_FOLLOW("팔로우 정보가 존재하지 않습니다.");

  private final String message;

  FollowErrorType(String message) {
    this.message = message;
  }
}
