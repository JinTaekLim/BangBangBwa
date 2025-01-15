package com.bangbangbwa.backend.domain.post.business;

import com.bangbangbwa.backend.domain.sns.common.dto.ReportPostDto;
import com.bangbangbwa.backend.domain.post.common.entity.ReportPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportPostGenerator {

    private final ReportPostParser reportPostParser;
    private final ReportPostUpdater reportPostUpdater;

    public ReportPost generate(ReportPostDto.Request request, Long memberId) {
        ReportPost reportPost = reportPostParser.requestToEntity(request);
        reportPostUpdater.updateCreatedId(reportPost, memberId);
        return reportPost;
    }
}
