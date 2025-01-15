package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class InvalidMemberVisibilityException extends BusinessException {

  private final String code;

  public InvalidMemberVisibilityException() {
    this(PostErrorType.INVALID_MEMBER_VISIBILITY.getMessage());
  }

  public InvalidMemberVisibilityException(String message) {
    super(message);
    this.code = PostErrorType.INVALID_MEMBER_VISIBILITY.name();
  }
}
