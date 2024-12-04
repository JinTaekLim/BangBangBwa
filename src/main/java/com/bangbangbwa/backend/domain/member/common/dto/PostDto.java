package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDto {

  private Long postId;
  private boolean isPinned;
  private String title;
  private String content;
  private String createdDate;
  private boolean hasImage;
  private boolean hasVideo;

  @Schema(name = "PostResponse", description = "게시글 목록 조회 응답")
  public record Response(
    @Schema(description = "게시글 정보 목록")
    List<PostResponse> postInfos
  ) {
  }

  @Schema(name = "PostResponses")
  public record PostResponse (
    @Schema(description = "게시글 ID")
    Long postId,
    @Schema(description = "고정 여부")
    boolean isPinned,
    @Schema(description = "제목")
    String title,
    @Schema(description = "내용")
    String content,
    @Schema(description = "작성일자")
    String createdDate,
    @Schema(description = "이미지 포함여부")
    boolean hasImage,
    @Schema(description = "동영상 포함여부")
    boolean hasVideo
  ) {
  }
}
