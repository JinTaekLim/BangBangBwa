package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MemberWallpaperDto {

  @Schema(name = "MemberWallpaperResponse", description = "회원 배경화면 응답 DTO")
  public record Response(
      @Schema(description = "배경화면 URL", example = "https://....")
      String wallpaper
  ) {

  }

}
