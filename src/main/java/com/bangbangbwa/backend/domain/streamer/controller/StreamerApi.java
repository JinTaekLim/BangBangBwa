package com.bangbangbwa.backend.domain.streamer.controller;

import com.bangbangbwa.backend.domain.streamer.common.dto.AddPlatformDto;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto.Response;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse200;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse401;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse403;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse500;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.MediaType;

@Tag(name = "StreamerAPI", description = "스트리머 API")
@ApiResponse500
public interface StreamerApi {

  @Operation(
      summary = "오늘의 한마디 작성",
      description = "오늘의 한마디를 저장합니다.",
      tags = {"StreamerAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CreateDailyMessageDto.Response.class)
              )
          ),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD_REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiResponse.class),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_MESSAGE",
                          summary = "메시지 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "message : 메세지를 입력해주세요."
                                ]
                              }
                              """
                      )
                  }
              )
          )
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Response> createDailyMessage(CreateDailyMessageDto.Request request);

  @Operation(
      summary = "스트리머 플랫폼 추가",
      description = "스트리머 플랫폼을 추가합니다.",
      tags = {"StreamerAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD_REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiResponse.class),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_PLATFORM_ID",
                          summary = "플랫폼 아이디 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "platformId : 플랫폼 아이디를 입력 바랍니다."
                                ]
                              }
                              """
                      ),
                      @ExampleObject(
                          name = "NOT_FOUND_PLATFORM_PROFILE_URL",
                          summary = "플랫폼 프로필 링크 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "platformProfileUrl : 플랫폼 프로필 링크를 입력 바랍니다."
                                ]
                              }
                              """
                      )
                  }
              )
          )
      }
  )
  @ApiResponse200
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Null> addStreamerPlatform(AddPlatformDto.Request request);

  @Operation(
      summary = "스트리머 플랫폼 추가",
      description = "스트리머 플랫폼을 추가합니다.",
      tags = {"StreamerAPI"}
  )
  @ApiResponse200
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Null> removeStreamerPlatform(Long platformId);
}
