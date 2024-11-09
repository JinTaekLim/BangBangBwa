package com.bangbangbwa.backend.domain.admin.exception.type;

import lombok.Getter;

@Getter
public enum AdminErrorType {

  NOT_FOUND_MEMBER("존재하지 않는 관리자입니다.");

  private final String message;

  AdminErrorType(String message) {
    this.message = message;
  }
}
