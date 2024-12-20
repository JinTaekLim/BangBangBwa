package com.bangbangbwa.backend.domain.post.business;

import com.bangbangbwa.backend.domain.post.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.repository.ReportPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportPostUpdater {

    private final ReportPostRepository reportPostRepository;

    public void updateCreatedId(ReportPost reportPost, Long memberId) {
        reportPost.updateCreatedId(memberId);
    }

    public void updateStatus(ReportPost reportPost) {
        reportPostRepository.updateStatus(reportPost);
    }
}
