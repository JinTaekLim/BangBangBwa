package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import org.springframework.stereotype.Component;

@Component
public class ReportCommentUpdater {

    public void updateCreatedId(ReportComment reportComment, Long memberId) {
        reportComment.updateCreatedId(memberId);
    }
}
