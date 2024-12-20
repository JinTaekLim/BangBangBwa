package com.bangbangbwa.backend.domain.comment.controller;

import com.bangbangbwa.backend.domain.comment.common.dto.AddCommentReplyDto;
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

@Tag(name = "CommentAPI", description = "댓글 API")
@ApiResponse500
public interface CommentApi {

  @Operation(
      summary = "답글 추가",
      description = "답글 추가하기",
      tags = {"CommentAPI"},
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "BAD_REQUEST",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiResponse.class),
                  examples = {
                      @ExampleObject(
                          name = "NOT_FOUND_COMMENT_ID",
                          summary = "댓글 아이디 미입력",
                          value = """
                              {
                              "code": "BAD_REQUEST",
                              "message": "잘못된 요청",
                              "data" : [
                                "commentId : 댓글 아이디를 입력 바랍니다."
                              ]
                              }
                              """
                      ),
                      @ExampleObject(
                          name = "NOT_FOUND_MESSAGE",
                          summary = "답글 내용 미입력",
                          value = """
                              {
                              "code": "BAD_REQUEST",
                              "message": "잘못된 요청",
                              "data" : [
                                "message : 답글 내용을 입력 바랍니다."
                              ]
                              }
                              """
                      ),
                  }
              )
          )
      }
  )
  @ApiResponse200
  @ApiResponse401
  @ApiResponse403
  ApiResponse<Null> addCommentReply(AddCommentReplyDto.Request request);
}
