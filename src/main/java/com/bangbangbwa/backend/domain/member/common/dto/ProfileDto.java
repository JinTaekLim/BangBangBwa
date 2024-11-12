package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.member.common.enums.Interest;
import java.util.List;

public class ProfileDto {
  public record Response(
    String imageUrl,
    String nickName,
    boolean isFollowing,
    String selfIntroduction,
    List<Interest> interests
  ) {
  }
}
