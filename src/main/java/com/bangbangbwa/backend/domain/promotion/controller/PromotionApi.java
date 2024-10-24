package com.bangbangbwa.backend.domain.promotion.controller;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "PromotionAPI", description = "홍보 API")
public interface PromotionApi {

  @Operation(
      tags = {"PromotionAPI"},
      summary = "배너 조회",
      description = "배너 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PromotionBannerDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<PromotionBannerDto.Response> getBanners();

  @Operation(
      tags = {"PromotionAPI"},
      summary = "추천 방송인 목록 조회",
      description = "추천 방송인 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PromotionStreamerDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<PromotionStreamerDto.Response> getStreamers();

}
