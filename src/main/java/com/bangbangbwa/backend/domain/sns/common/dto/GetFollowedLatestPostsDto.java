package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class GetFollowedLatestPostsDto {

  @Schema(name = "GetFollowedLatestPostsDto_Response", description = "팔로우한 사용자 최신글 조회 반환")
  public record Response(
      @Schema(description = "방송인ID")
      Long broadcasterId,
      @Schema(description = "프로필 사진")
      String profileUrl,
      @Schema(description = "닉네임")
      String nickname,
      @Schema(description = "오늘의 한마디")
      String todaySaying,
      @Schema(description = "최근 게시물")
      List<RecentPost> recentPostList
  ) {}

  public record RecentPost(
      @Schema(description = "제목")
      String title,
      @Schema(description = "내용")
      String content,
      @Schema(description = "내가 작성한 댓글")
      String comment,
      @Schema(description = "팔로우 여부")
      boolean isFollowed
  ) {}
}
