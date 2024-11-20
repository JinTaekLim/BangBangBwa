package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.member.common.enums.Interest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileDto {

  private Long memberId;
  private Long currentMemberId;
  private String imageUrl;
  private String nickname;
  private Boolean isFollowing;
  private String selfIntroduction;
  private List<Interest> interests;

  public ProfileDto(Long memberId, Long currentMemberId) {
    this.memberId = memberId;
    this.currentMemberId = currentMemberId;
  }

  @Schema(name = "ProfileResponse", description = "프로필 정보 조회 응답")
  public record Response(
    @Schema(description = "이미지 URL")
    String imageUrl,
    @Schema(description = "닉네임")
    String nickname,
    @Schema(description = "팔로잉 여부")
    boolean isFollowing,
    @Schema(description = "자기소개")
    String selfIntroduction,
    @Schema(description = "관심분야 목록")
    List<Interest> interests
  ) {
  }
}
