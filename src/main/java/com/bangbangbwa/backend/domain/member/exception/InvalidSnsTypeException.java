package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class InvalidSnsTypeException extends BusinessException {

  private final String code;

  public InvalidSnsTypeException() {
    this(MemberErrorType.INVALID_SNS_TYPE_ERROR.getMessage());
  }

  public InvalidSnsTypeException(final String message) {
    super(message);
    this.code = MemberErrorType.INVALID_SNS_TYPE_ERROR.name();
  }

}
