package com.bangbangbwa.backend.domain.member.controller;

import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.annotation.swagger.ApiCommonResponse;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "MemberAPI", description = "회원 API")
@ApiCommonResponse
public interface MemberApi {

  @Operation(summary = "회원가입", tags = {"MemberAPI"})
  ApiResponse<TokenDto> signup(
      @Parameter SnsType snsType,
      @RequestBody MultipartFile file,
      @RequestBody MemberSignupDto.Request request
  );
}
