package com.bangbangbwa.backend.domain.sns.exception.type;

import lombok.Getter;

@Getter
public enum PostErrorType {
  NO_POST_PERMISSION("작성 권한이 없습니다.");


  private final String message;

  PostErrorType(String message) {
    this.message = message;
  }
}
