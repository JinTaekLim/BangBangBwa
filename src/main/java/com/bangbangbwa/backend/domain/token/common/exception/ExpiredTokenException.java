package com.bangbangbwa.backend.domain.token.common.exception;

import com.bangbangbwa.backend.domain.token.common.exception.type.TokenErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import java.io.Serial;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends UnAuthenticatedException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String code;

  public ExpiredTokenException() {
    super(TokenErrorType.EXPIRED_TOKEN.getMessage());
    this.code = TokenErrorType.EXPIRED_TOKEN.name();
  }

  public ExpiredTokenException(final String message) {
    super(message);
    this.code = TokenErrorType.EXPIRED_TOKEN.name();
  }
}
