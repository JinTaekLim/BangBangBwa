package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class DuplicatedNicknameException extends BusinessException {

  private final String code;

  public DuplicatedNicknameException() {
    this(MemberErrorType.DUPLICATED_NICKNAME_ERROR.getMessage());
  }

  public DuplicatedNicknameException(final String message) {
    super(message);
    this.code = MemberErrorType.DUPLICATED_NICKNAME_ERROR.name();
  }
}
