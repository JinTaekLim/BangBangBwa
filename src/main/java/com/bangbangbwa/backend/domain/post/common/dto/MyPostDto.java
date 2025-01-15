package com.bangbangbwa.backend.domain.post.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class MyPostDto {

  @Schema(name = "MyPostResponse", description = "내 게시물 응답 DTO")
  public record Response(
      @Schema(description = "내 게시물 게시물 정보")
      MyPostResponsePostInfo postInfo,
      @Schema(description = "내 게시물 본 스트리머 정보 목록")
      List<MyPostResponseReadStreamerInfo> readStreamersInfoList,
      @Schema(description = "내 게시물 댓글 정보 목록")
      List<MyPostResponseCommentInfo> commentInfoList
  ) {

  }

  @Schema(name = "MyPostResponsePostInfo", description = "내 게시물 게시물 정보 DTO")
  public record MyPostResponsePostInfo(
      @Schema(description = "게시물 아아디")
      Long postId,
      @Schema(description = "게시물 작성자 아이디")
      Long postMemberId,
      @Schema(description = "게시물 작성자 프로필 이미지")
      String postMemberProfile,
      @Schema(description = "게시물 작성자 닉네임")
      String postMemberNickname,
      @Schema(description = "게시물 제목")
      String postTitle,
      @Schema(description = "게시물 내용")
      String postContent
  ) {

  }

  @Schema(name = "MyPostReadStreamerInfo", description = "내 게시물 본 스트리머 정보 DTO")
  public record MyPostResponseReadStreamerInfo(
      @Schema(description = "스트리머 아이디")
      Long streamerId,
      @Schema(description = "스트리머 프로필 이미지")
      String streamerImage,
      @Schema(description = "스트리머 닉네임")
      String streamerNickname
  ) {

  }

  @Schema(name = "MyPostResponseCommentInfo", description = "내 게시물 댓글 정보 DTO")
  public record MyPostResponseCommentInfo(
      @Schema(description = "댓글 아이디")
      Long commentId,
      @Schema(description = "댓글 작성자 아이디")
      Long commentMemberId,
      @Schema(description = "댓글 작성자 프로필 이미지")
      String commentMemberProfile,
      @Schema(description = "댓글 작성자 닉네임")
      String commentMemberNickname,
      @Schema(description = "댓글 내용")
      String commentContent,
      @Schema(description = "답글 내용")
      String commentReply
  ) {

  }
}
