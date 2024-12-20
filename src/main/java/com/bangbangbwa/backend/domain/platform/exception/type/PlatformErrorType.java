package com.bangbangbwa.backend.domain.platform.exception.type;

import lombok.Getter;

@Getter
public enum PlatformErrorType {

  NON_RELATION("스트리머에 추가된 플랫폼이 없습니다."),
  ;

  private final String message;

  PlatformErrorType(String message) {
    this.message = message;
  }
}
