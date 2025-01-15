package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.dto.ReportCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCommentGenerator {

    private final ReportCommentParser reportCommentParser;
    private final ReportCommentUpdater reportCommentUpdater;

    public ReportComment generate(ReportCommentDto.Request request, Long memberId) {
        ReportComment reportComment = reportCommentParser.requestToEntity(request);
        reportCommentUpdater.updateCreatedId(reportComment, memberId);
        return reportComment;
    }
}
