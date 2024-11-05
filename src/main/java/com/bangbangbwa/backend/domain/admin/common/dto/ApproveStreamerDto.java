package com.bangbangbwa.backend.domain.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ApproveStreamerDto {

  @Schema(name = "ApproveStreamerRequest", description = "계정 등업 승인 요청 DTO")
  public record Request(

      @Schema(description = "승인 대기 ID")
      @NotNull(message = "승인 대기 ID를 입력헤주세요.")
      Long pendingStreamerId
  ) {

  }

  @Schema(name = "ApproveStreamerResponse", description = "계정 등업 승인 반환 DTO")
  public record Response(
      @Schema(description = "승인 대기 ID")
      Long pendingStreamerId
  ) {

  }
}
