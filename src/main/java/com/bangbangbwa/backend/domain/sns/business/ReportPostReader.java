package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.common.enums.ReportStatus;
import com.bangbangbwa.backend.domain.sns.repository.ReportPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportPostReader {

    private final ReportPostRepository reportPostRepository;

    public Optional<ReportPost> findPendingReportByPostIdAndCreatedId(Long postId, Long createdId) {
        return reportPostRepository.findByPostIdAndCreatedId(postId, createdId.toString(), ReportStatus.PENDING);
    }
}
