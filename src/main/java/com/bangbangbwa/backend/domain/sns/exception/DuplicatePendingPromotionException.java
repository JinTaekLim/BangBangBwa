package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class DuplicatePendingPromotionException extends BusinessException {

  private final String code;

  public DuplicatePendingPromotionException() {
    this(PostErrorType.DUPLICATE_PENDING_PROMOTION.getMessage());
  }

  public DuplicatePendingPromotionException(String message) {
    super(message);
    this.code = PostErrorType.DUPLICATE_PENDING_PROMOTION.name();
  }
}
