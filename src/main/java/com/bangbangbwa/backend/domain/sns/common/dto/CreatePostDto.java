package com.bangbangbwa.backend.domain.sns.common.dto;

import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreatePostDto {

  @Schema(name = "CreatePostRequest", description = "글 작성 요청 DTO")
  public record Request(

      @Schema(description = "게시글 타입", examples = "MEMBER")
      @NotNull(message = "게시글 타입을 입력해주세요. ")
      PostType postType,

      @Schema(description = "제목", examples = "title")
      @NotBlank(message = "제목을 입력해주세요.")
      String title,

      @Schema(description = "내용", examples = "content")
      @NotBlank(message = "내용을 입력해주세요.")
      String content,

      @Schema(description = "게시글 공개 대상 설정", example = "[1, 2]")
      List<Long> publicMembers,

      @Schema(description = "게시글 비공개 대상 설정", example = "[1, 2]")
      List<Long> privateMembers
  ){}

  @Schema(name = "CreatePostResponse", description = "글 작성 반환 DTO")
  public record Response(
      @Schema(description = "게시글 타입")
      PostType postType,
      @Schema(description = "제목")
      String title,
      @Schema(description = "내용")
      String content
  ){}
}
