package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import com.bangbangbwa.backend.domain.sns.repository.ReportCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportCommentCreator {

    private final ReportCommentRepository reportCommentRepository;

    public void save(ReportComment reportComment) {
        reportCommentRepository.save(reportComment);
    }
}
