package com.bangbangbwa.backend.domain;

import com.bangbangbwa.backend.global.annotation.swagger.ApiCommonResponse;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils.Null;

@Tag(name = "Basic (기본) API", description = "기본 제공 API")
@ApiCommonResponse
public interface BasicApi {

  @Operation(summary = "서비스 정상 여부 확인", tags = {"Basic (기본) API"})
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ApiResponse.class),
          examples = @ExampleObject(
              value = """
                  {
                    "code": "OK",
                    "message": "OK",
                    "data": "The server is available"
                  }"""
          )
      )
  )
  ApiResponse<String> healthCheck();

  @Operation(summary = "예외 핸들링", tags = {"Basic (기본) API"})
  ApiResponse<Null> error(@Parameter String name);
}