package com.bangbangbwa.backend.domain.token.common.exception;

import com.bangbangbwa.backend.domain.token.common.exception.type.TokenErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import java.io.Serial;
import lombok.Getter;

@Getter
public class InvalidTokenException extends UnAuthenticatedException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String code;

  public InvalidTokenException() {
    super(TokenErrorType.INVALID_TOKEN.getMessage());
    this.code = TokenErrorType.INVALID_TOKEN.name();
  }

  public InvalidTokenException(final String message) {
    super(message);
    this.code = TokenErrorType.INVALID_TOKEN.name();
  }
}
