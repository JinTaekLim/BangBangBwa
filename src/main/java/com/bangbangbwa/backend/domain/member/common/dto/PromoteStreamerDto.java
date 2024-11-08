package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class PromoteStreamerDto {


  @Schema(name = "PromoteStreamerRequest", description = "방송 계정 등업 요청 DTO")
  public record Request(

      @Schema(description = "방송국 URL", examples = "https://www.abc.com")

      @NotBlank(message = "방송국 URL을 입력해주세요.")
      String platformUrl
  ) {

  }

  @Schema(name = "PromoteStreamerResponse", description = "방송 계정 등업 반환 DTO")
  public record Response(

      @Schema(description = "방송국 URL")
      String platformUrl
  ) {

  }


}
