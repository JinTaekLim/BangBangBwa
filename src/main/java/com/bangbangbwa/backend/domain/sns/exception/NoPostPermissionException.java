package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NoPostPermissionException extends BusinessException {

  private final String code;

  public NoPostPermissionException() {
    this(PostErrorType.NO_POST_PERMISSION.getMessage());
  }

  public NoPostPermissionException(String message) {
    super(message);
    this.code = PostErrorType.NO_POST_PERMISSION.name();
  }
}
