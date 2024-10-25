package com.bangbangbwa.backend.domain.sns.controller;

import com.bangbangbwa.backend.domain.sns.common.dto.GetFollowedLatestPostsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetFollowedLatestPostsDto.Response;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostListDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "SnsAPI", description = "SNS API")
public interface SnsApi {

  @Operation(
      tags = {"SnsAPI"},
      summary = "팔로우한 사용자 최신글 조회",
      description = "팔로우한 사용자의 최신글 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetFollowedLatestPostsDto.Response.class)
              )
          )
      }
  )
  public ApiResponse<Response> getFollowedLatestPosts();

  @Operation(
      tags = {"SnsAPI"},
      summary = "게시글 목록 조회",
      description = "게시글 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetPostListDto.Response.class)
              )
          )
      }
  )
  public ApiResponse<List<GetPostListDto.Response>> getPostList();

  @Operation(
      tags = {"SnsAPI"},
      summary = "게시글 상세 조회",
      description = "게시글 상세 정보를 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetPostDetailsDto.Response.class)
              )
          )
      }
  )
  public ApiResponse<GetPostDetailsDto.Response> getPostDetails(@Parameter Long postId);

  }
