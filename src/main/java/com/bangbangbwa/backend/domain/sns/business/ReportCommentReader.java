package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import com.bangbangbwa.backend.domain.sns.common.enums.ReportStatus;
import com.bangbangbwa.backend.domain.sns.repository.ReportCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportCommentReader {

    private final ReportCommentRepository reportCommentRepository;

    public Optional<ReportComment> findPendingReportByCommentIdAndCreatedId(Long commentId, Long createdId) {
        return reportCommentRepository.findByCommentIdAndCreatedId(
                commentId,
                createdId.toString(),
                ReportStatus.PENDING
        );
    }
}
