package com.bangbangbwa.backend.domain.sns.common.entity;

import com.bangbangbwa.backend.domain.post.common.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("reportComment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportComment {

    private Long id;
    private ReportStatus status;
    private Long commentId;
    private String createdId;
    private LocalDateTime createdAt;
    private String updatedId;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Builder
    public ReportComment(Long commentId, String createdId) {
        this.status = ReportStatus.PENDING;
        this.commentId = commentId;
        this.createdId = createdId;
        this.createdAt = LocalDateTime.now();
    }

    public void updateCreatedId(Long memberId) {
        this.createdId = memberId.toString();
    }
}
