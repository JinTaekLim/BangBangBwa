package com.bangbangbwa.backend.domain.streamer.common.exception;

import com.bangbangbwa.backend.domain.streamer.common.exception.type.StreamerErrorType;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotFoundStreamerException extends BusinessException {

  private final String code;

  public NotFoundStreamerException() {
    this(StreamerErrorType.NOT_FOUND_STREAMER.getMessage());
  }

  public NotFoundStreamerException(String message) {
    super(message);
    this.code = StreamerErrorType.NOT_FOUND_STREAMER.name();
  }
}
