package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class MemberUpdateDto {

  @Schema(name = "MemberUpdateRequest", description = "회원 정보 수정 요청 DTO")
  public record Request(
      String nickname,
      String selfIntroduction,
      List<String> tagList
  ) {

  }
}
