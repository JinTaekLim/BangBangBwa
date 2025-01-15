package com.bangbangbwa.backend.domain.streamer.common.exception.type;

import lombok.Getter;

@Getter
public enum StreamerErrorType {
  NOT_FOUND_STREAMER("존재하지 않는 방송인 입니다.");


  private final String message;

  StreamerErrorType(String message) {
    this.message = message;
  }
}
