package com.bangbangbwa.backend.domain;

import com.bangbangbwa.backend.global.annotation.ApiCommonResponse;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Basic (기본) API", description = "기본 제공 API")
@ApiCommonResponse
public interface BasicApi {

  @Operation(summary = "서비스 정상 여부 확인", tags = {"Basic (기본) API"})
  ApiResponse<?> healthCheck();

  @Operation(summary = "예외 핸들링", tags = {"Basic (기본) API"})
  ApiResponse<?> error(@Parameter String name);

  @Operation(summary = "파일 업로드", tags = {"Basic (기본) API"})
  ApiResponse<?> fileUpload(@Parameter MultipartFile file);
}