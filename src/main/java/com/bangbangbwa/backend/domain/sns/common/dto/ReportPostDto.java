package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ReportPostDto {

    @Schema(name = "ReportPostRequest", description = "게시물 신고 요청 DTO")
    public record Request(
            @Schema(description = "게시글 ID", examples = "1,2")
            @NotNull(message = "게시물 ID를 입력해주세요.")
            Long postId
    ){}
}
