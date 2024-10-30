package com.bangbangbwa.backend.domain.token.common.exception;

import com.bangbangbwa.backend.domain.token.common.exception.type.TokenErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import lombok.Getter;

@Getter
public class InvalidJwtSignatureException extends UnAuthenticatedException {

  private final String code;

  public InvalidJwtSignatureException() {
    super(TokenErrorType.INVALID_JWT_SIGNATURE.getMessage());
    this.code = TokenErrorType.INVALID_JWT_SIGNATURE.name();
  }

  public InvalidJwtSignatureException(final String message) {
    super(message);
    this.code = TokenErrorType.INVALID_JWT_SIGNATURE.name();
  }
}
