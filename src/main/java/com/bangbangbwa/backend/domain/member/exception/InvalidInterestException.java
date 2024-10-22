package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class InvalidInterestException extends BusinessException {

  private final String code;

  public InvalidInterestException() {
    this(MemberErrorType.INVALID_INTEREST_ERROR.getMessage());
  }

  public InvalidInterestException(final String message) {
    super(message);
    this.code = MemberErrorType.INVALID_INTEREST_ERROR.name();
  }
}
