package com.bangbangbwa.backend.domain.post.controller;

import com.bangbangbwa.backend.domain.post.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.post.common.dto.CreatePostDto.Response;
import com.bangbangbwa.backend.domain.post.common.dto.GetLatestPostsDto;
import com.bangbangbwa.backend.domain.post.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.post.common.dto.GetPostListDto;
import com.bangbangbwa.backend.domain.post.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse200;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse401;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse403;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse500;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "PostAPI", description = "게시물 API")
@ApiResponse500
public interface PostApi {

  @Operation(
      summary = "게시물 작성",
      description = "작성된 게시물을 저장합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CreatePostDto.Response.class)
              )
          ),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = ApiResponse.class)),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_POST_TYPE",
                          summary = "게시글 타입 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "postType : 게시글 타입을 입력해주세요"
                                ]
                              }
                              """
                      ),
                      @ExampleObject(
                          name = "NOT_FOUND_TITLE",
                          summary = "제목 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "title : 제목을 입력해주세요"
                                ]
                              }
                              """
                      ),
                      @ExampleObject(
                          name = "NOT_FOUND_CONTENT",
                          summary = "내용 미입력",
                          value = """
                              {
                                "code" : "BAD_REQUEST",
                                "message" : "잘못된 요청입니다.",
                                "data" : [
                                  "content : 내용을 입력해주세요"
                                ]
                              }
                              """
                      ),
                  }
              )
          ),
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Response> createPost(CreatePostDto.Request request);

  @Operation(
      summary = "게시물 파일 업로드",
      description = "게시된 파일을 업로드합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = UploadPostMediaDto.Response.class)
              )
          ),
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<UploadPostMediaDto.Response> uploadPostMedia(MultipartFile file);

  @Operation(
      summary = "게시물 목록 조회",
      description = "게시물 목록을 조회합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetPostListDto.Response.class)
              )
          ),
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<List<GetPostListDto.Response>> getPostList();

  @Operation(
      summary = "게시물 상세 조회",
      description = "게시물 상세를 조회합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetPostDetailsDto.Response.class)
              )
          ),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = ApiResponse.class)),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_POST",
                          summary = "미등록 게시물",
                          value = """
                              {
                                "code" : "NOT_FOUND_POST",
                                "message" : "존재하지 않는 게시물 입니다.",
                                "data" : null
                              }
                              """
                      ),
                  }
              )
          ),
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<GetPostDetailsDto.Response> getPostDetails(Long postId);

  @Operation(
      summary = "최근 게시물 목록 조회",
      description = "최근 게시물 목록을 조회합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetLatestPostsDto.Response.class)
              )
          ),
      }
  )
  @ApiResponse401
  @ApiResponse403
  ApiResponse<List<GetLatestPostsDto.Response>> getLatestPosts();

  @Operation(
      summary = "내 게시물 조회",
      description = "내 게시물 조회 페이지 API",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetLatestPostsDto.Response.class)
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
                          name = "NOT_FOUND_POST_OR_PERMISSION",
                          summary = "비작성자 접근",
                          value = """
                              {
                                "code" : "NOT_FOUND_POST_OR_PERMISSION",
                                "message" : "존재하지 않거나 작성 권한이 없는 게시물입니다.",
                                "data" : null
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
  ApiResponse<?> getMyPost(Long postId);

  @Operation(
      summary = "게시물 삭제",
      description = "해당 게시물을 삭제합니다.",
      tags = {"PostAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = ApiResponse.class)),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_POST",
                          summary = "미등록 게시물",
                          value = """
                              {
                                "code" : "NOT_FOUND_POST",
                                "message" : "존재하지 않는 게시물 입니다.",
                                "data" : null
                              }
                              """
                      ),
                  }
              )
          ),
      }
  )
  @ApiResponse200
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Null> deletePost(Long postId);
}
