package com.bangbangbwa.backend.domain.token.common.exception;


import com.bangbangbwa.backend.domain.token.common.exception.type.TokenErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;

public class NotFoundTokenException extends UnAuthenticatedException {

  private final String code;

  public NotFoundTokenException() {
    super(TokenErrorType.NOT_FOUND_TOKEN.getMessage());
    this.code = TokenErrorType.NOT_FOUND_TOKEN.name();
  }

  public NotFoundTokenException(final String message) {
    super(message);
    this.code = TokenErrorType.NOT_FOUND_TOKEN.name();
  }
}
