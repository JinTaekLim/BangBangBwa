package com.bangbangbwa.backend.domain.streamer.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CreateDailyMessageDto {
  @Schema(name = "CreateDailyMessageRequest", description = "오늘의 한마디 작성 요청 DTO")
  public record Request(
      @Schema(description = "메세지", examples = "Message")
      @NotNull(message = "메세지를 입력해주세요.")
      String message
  ){}

  @Schema(name = "CreateDailyMessageResponse", description = "오늘의 한마디 작성 반환 DTO")
  public record Response(
      @Schema(description = "메세지")
      String message
  ){}

}
