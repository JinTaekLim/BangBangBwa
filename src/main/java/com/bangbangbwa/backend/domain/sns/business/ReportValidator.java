package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.post.business.ReportPostReader;
import com.bangbangbwa.backend.domain.sns.exception.DuplicateReportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportValidator {

    private final ReportPostReader reportPostReader;
    private final ReportCommentReader reportCommentReader;

    public void checkForDuplicateReportPost(Long postId, Long createdId) {
        reportPostReader.findPendingReportByPostIdAndCreatedId(postId, createdId)
                .ifPresent(reportPost -> {
                    throw new DuplicateReportException();
                });
    }

    public void checkForDuplicateReportComment(Long commentId, Long createdId) {
        reportCommentReader.findPendingReportByCommentIdAndCreatedId(commentId, createdId)
                .ifPresent(reportComment -> {
                    throw new DuplicateReportException();
                });
    }
}
