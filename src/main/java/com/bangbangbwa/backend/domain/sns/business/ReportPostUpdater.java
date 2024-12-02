package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import org.springframework.stereotype.Component;

@Component
public class ReportPostUpdater {

    public void updateCreatedId(ReportPost reportPost, Long memberId) {
        reportPost.updateCreatedId(memberId);
    }
}
