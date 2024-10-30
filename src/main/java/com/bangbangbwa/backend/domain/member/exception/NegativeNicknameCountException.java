package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NegativeNicknameCountException extends BusinessException {

  private final String code;

  public NegativeNicknameCountException() {
    this(MemberErrorType.NEGATIVE_NICKNAME_COUNT.getMessage());
  }

  public NegativeNicknameCountException(final String message) {
    super(message);
    this.code = MemberErrorType.NEGATIVE_NICKNAME_COUNT.name();
  }
}
