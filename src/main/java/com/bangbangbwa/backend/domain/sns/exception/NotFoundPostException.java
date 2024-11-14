package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundPostException extends BusinessException {

  private final String code;

  public NotFoundPostException() {
    this(PostErrorType.NOT_FOUND_POST.getMessage());
  }

  public NotFoundPostException(String message) {
    super(message);
    this.code = PostErrorType.NOT_FOUND_POST.name();
  }
}
