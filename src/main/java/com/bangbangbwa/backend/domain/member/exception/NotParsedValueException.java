package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.ServerException;
import lombok.Getter;

@Getter
public class NotParsedValueException extends ServerException {

  private final String code;

  public NotParsedValueException() {
    this(MemberErrorType.NOT_PARSED_VALUE_ERROR.getMessage());
  }

  public NotParsedValueException(String message) {
    super(message);
    this.code = MemberErrorType.NOT_PARSED_VALUE_ERROR.name();
  }
}
