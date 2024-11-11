package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.ServerException;
import lombok.Getter;

@Getter
public class AuthenticationNameNullException extends ServerException {

  private final String code;

  public AuthenticationNameNullException() {
    this(MemberErrorType.AUTHENTICATION_NAME_NULL_ERROR.getMessage());
  }

  public AuthenticationNameNullException(String message) {
    super(message);
    this.code = MemberErrorType.AUTHENTICATION_NAME_NULL_ERROR.name();
  }
}
