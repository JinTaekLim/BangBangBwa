package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NoPostPermission extends BusinessException {

  private final String code;

  public NoPostPermission() {
    this(MemberErrorType.NOT_PARSED_VALUE_ERROR.getMessage());
  }

  public NoPostPermission(String message) {
    super(message);
    this.code = MemberErrorType.NOT_PARSED_VALUE_ERROR.name();
  }
}
