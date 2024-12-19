package com.bangbangbwa.backend.domain.platform.controller;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformListDto;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse401;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse403;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse500;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "PlatformAPI", description = "플랫폼 API")
@ApiResponse500
public interface PlatformApi {

  @Operation(
      summary = "플랫폼 목록 조회",
      description = "플랫폼 목록을 조회합니다.",
      tags = {"PlatformAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PlatformListDto.Response.class)
              )
          )
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<PlatformListDto.Response> platformList();
}
