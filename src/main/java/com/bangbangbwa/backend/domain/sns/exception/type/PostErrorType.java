package com.bangbangbwa.backend.domain.sns.exception.type;

import lombok.Getter;

@Getter
public enum PostErrorType {
  NO_POST_PERMISSION("작성 권한이 없습니다."),
  NOT_FOUND_POST("존재하지 않는 게시물 입니다."),
  INVALID_MEMBER_VISIBILITY("공개/비공개 값은 함께 전달할 수 없습니다.");


  private final String message;

  PostErrorType(String message) {
    this.message = message;
  }
}
