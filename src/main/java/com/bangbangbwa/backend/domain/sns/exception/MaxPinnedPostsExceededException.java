package com.bangbangbwa.backend.domain.sns.exception;

import com.bangbangbwa.backend.domain.sns.exception.type.PostErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class MaxPinnedPostsExceededException extends BusinessException {

  private final String code;

  public MaxPinnedPostsExceededException() {
    this(PostErrorType.MAX_PINNED_POSTS_EXCEEDED.getMessage());
  }

  public MaxPinnedPostsExceededException(String message) {
    super(message);
    this.code = PostErrorType.MAX_PINNED_POSTS_EXCEEDED.name();
  }
}
