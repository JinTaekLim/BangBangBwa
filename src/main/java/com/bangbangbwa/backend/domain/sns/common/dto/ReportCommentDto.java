package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ReportCommentDto {

    @Schema(name = "ReportCommentRequest", description = "댓글 신고 요청 DTO")
    public record Request(
            @Schema(description = "댓글 ID", examples = "1,2")
            @NotNull(message = "댓글 ID를 입력해주세요.")
            Long commentId
    ){}
}
