package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.exception.DuplicateReportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportValidator {

    private final ReportPostReader reportPostReader;

    public void checkForDuplicateReportPost(Long postId, Long createdId) {
        reportPostReader.findPendingReportByPostIdAndCreatedId(postId, createdId)
                .ifPresent(reportPost -> {
                    throw new DuplicateReportException();
                });
    }
}
