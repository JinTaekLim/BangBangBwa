package com.bangbangbwa.backend.domain.member.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import lombok.Getter;

@Getter
public class NotUniqueMemberException extends UnAuthenticatedException {

  private final String code;

  public NotUniqueMemberException() {
    this(MemberErrorType.NOT_UNIQUE_MEMBER.getMessage());
  }

  public NotUniqueMemberException(final String message) {
    super(message);
    this.code = MemberErrorType.NOT_UNIQUE_MEMBER.name();
  }
}
