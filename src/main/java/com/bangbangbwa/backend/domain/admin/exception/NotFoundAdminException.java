package com.bangbangbwa.backend.domain.admin.exception;

import com.bangbangbwa.backend.domain.admin.exception.type.AdminErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundAdminException extends BusinessException {

  private final String code;

  public NotFoundAdminException() {
    this(AdminErrorType.NOT_FOUND_MEMBER.getMessage());
  }

  public NotFoundAdminException(final String message) {
    super(message);
    this.code = AdminErrorType.NOT_FOUND_MEMBER.name();
  }
}
