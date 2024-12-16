package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundPostOrPermissionException extends BusinessException {

  private final String code;

  public NotFoundPostOrPermissionException() {
    this(PostErrorType.NOT_FOUND_POST_OR_PERMISSION.getMessage());
  }

  public NotFoundPostOrPermissionException(String message) {
    super(message);
    this.code = PostErrorType.NOT_FOUND_POST_OR_PERMISSION.name();
  }
}
