package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class MemberUpdateDto {

  @Schema(name = "MemberUpdateRequest", description = "회원 정보 수정 요청 DTO")
  public record Request(
      @Schema(description = "회원 닉네임", example = "닉네임")
      String nickname,
      @Schema(description = "회원 자기 소개", example = "안녕하세요 ***입니다.")
      String selfIntroduction,
      @Schema(description = "일반 회원 - 관심태그, 스트리머 - 방송분야", example = "[ \"게임\", \"여행\", \"마인크래프트\" ]")
      List<String> tagList
  ) {

  }
}
