package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import lombok.Getter;

@Getter
public class AuthenticationNullException extends UnAuthenticatedException {

  private final String code;

  public AuthenticationNullException() {
    this(MemberErrorType.AUTHENTICATION_NULL_ERROR.getMessage());
  }

  public AuthenticationNullException(String message) {
    super(message);
    this.code = MemberErrorType.AUTHENTICATION_NULL_ERROR.name();
  }
}
