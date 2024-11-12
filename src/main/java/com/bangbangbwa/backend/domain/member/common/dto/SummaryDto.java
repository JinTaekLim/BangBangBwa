package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.promotion.common.enums.Platform;
import java.util.List;

public class SummaryDto {
  public record Response(
    Long followerCount,
    Long followingCount,
    Long postCount,
    boolean isStreamer,
    boolean isSubmittedToStreamer,
    List<Platform> platforms
  ) {
  }
}
