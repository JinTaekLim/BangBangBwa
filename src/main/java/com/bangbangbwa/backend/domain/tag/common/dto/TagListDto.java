package com.bangbangbwa.backend.domain.tag.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class TagListDto {

  @Schema(name = "TagListResponse", description = "태그 목록 조회 응답 DTO")
  public record Response(

      @Schema(description = "태그 목록", example = "[ \"장조림\",\"장면\",\"장구\",\"장치\",\"구장소\"]")
      List<String> tagList
  ) {

  }
}
