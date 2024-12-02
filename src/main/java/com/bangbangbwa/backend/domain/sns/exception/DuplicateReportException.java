package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class DuplicateReportException extends BusinessException {

  private final String code;

  public DuplicateReportException() {
    this(PostErrorType.DUPLICATE_REPORT.getMessage());
  }

  public DuplicateReportException(String message) {
    super(message);
    this.code = PostErrorType.DUPLICATE_REPORT.name();
  }
}
