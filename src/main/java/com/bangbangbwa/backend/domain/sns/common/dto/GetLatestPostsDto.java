package com.bangbangbwa.backend.domain.sns.common.dto;

import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetLatestPostsDto {

  @Schema(description = "방송인ID")
  Long streamerId;
  @Schema(description = "프로필 사진")
  String profileUrl;
  @Schema(description = "최근 게시물")
  String postIdList;

  public Response toResponse(DailyMessage dailyMessage) {
    return new Response(
        this.streamerId,
        this.profileUrl,
        dailyMessage.getMessage(),
        convertPostIdsToList(this.postIdList)
    );
  }


  private List<Long> convertPostIdsToList(String postIdList) {
    return Arrays.stream(postIdList.split(","))
        .map(Long::valueOf)
        .collect(Collectors.toList());
  }

  @Schema(name = "GetLatestPostsDto_Response", description = "사용자 최신글 조회 반환")
  public record Response(
      @Schema(description = "방송인ID")
      Long streamerId,
      @Schema(description = "프로필 사진")
      String profileUrl,
      @Schema(description = "오늘의 한마디")
      String dailyMessage,
      @Schema(description = "최근 게시물")
      List<Long> postIdList
  ) {}
}
