package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.FollowErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundFollowException extends BusinessException {

  private final String code;

  public NotFoundFollowException() {
    this(FollowErrorType.NOT_FOUND_FOLLOW.getMessage());
  }

  public NotFoundFollowException(String message) {
    super(message);
    this.code = FollowErrorType.NOT_FOUND_FOLLOW.name();
  }
}
