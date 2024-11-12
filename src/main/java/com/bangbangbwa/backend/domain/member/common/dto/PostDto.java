package com.bangbangbwa.backend.domain.member.common.dto;

public class PostDto {
  public record Response(
    Long postId,
    boolean isPinned,
    String title,
    String content,
    String createdDate,
    boolean hasImage,
    boolean hasVideo
  ) {
  }
}
