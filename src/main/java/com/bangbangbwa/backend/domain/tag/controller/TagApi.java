package com.bangbangbwa.backend.domain.tag.controller;

import com.bangbangbwa.backend.domain.tag.common.dto.TagListDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "TagAPI", description = "관심 태그 API")
public interface TagApi {

  @Operation(
      tags = {"TagAPI"},
      summary = "태그 목록 조회",
      description = "입력한 태그 관련 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = TagListDto.Response.class)
              )
          )
      }
  )
  ApiResponse<TagListDto.Response> tagList(@Parameter String tagWord);
}
