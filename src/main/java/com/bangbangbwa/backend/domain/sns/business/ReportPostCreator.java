package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.repository.ReportPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPostCreator {

    private final ReportPostRepository reportPostRepository;

    public void save(ReportPost reportPost) {
        reportPostRepository.save(reportPost);
    }
}
