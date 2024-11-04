package com.bangbangbwa.backend.domain.sns.exception.type;

import lombok.Getter;

@Getter
public enum PostErrorType {
  NO_POST_PERMISSION("작성 권한이 없습니다."),
  NOT_FOUND_POST("존재하지 않는 게시물 입니다.");


  private final String message;

  PostErrorType(String message) {
    this.message = message;
  }
}
