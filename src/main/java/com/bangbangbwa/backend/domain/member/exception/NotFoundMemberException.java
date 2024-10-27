package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundMemberException extends BusinessException {

  private final String code;

  public NotFoundMemberException() {
    this(MemberErrorType.NOT_FOUND_MEMBER.getMessage());
  }

  public NotFoundMemberException(final String message) {
    super(message);
    this.code = MemberErrorType.NOT_FOUND_MEMBER.name();
  }
}
