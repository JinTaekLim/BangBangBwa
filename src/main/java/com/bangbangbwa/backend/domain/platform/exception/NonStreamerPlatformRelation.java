package com.bangbangbwa.backend.domain.platform.exception;

import com.bangbangbwa.backend.domain.platform.exception.type.PlatformErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NonStreamerPlatformRelation extends BusinessException {

  private final String code;

  public NonStreamerPlatformRelation() {
    this(PlatformErrorType.NON_RELATION.getMessage());
  }

  public NonStreamerPlatformRelation(final String message) {
    super(message);
    this.code = PlatformErrorType.NON_RELATION.name();
  }
}
