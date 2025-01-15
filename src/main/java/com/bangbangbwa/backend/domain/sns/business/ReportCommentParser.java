package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.dto.ReportCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import com.bangbangbwa.backend.domain.sns.common.mapper.ReportCommentMapper;
import org.springframework.stereotype.Component;

@Component
public class ReportCommentParser {

    public ReportComment requestToEntity(ReportCommentDto.Request request) {
        return ReportCommentMapper.INSTANCE.dtoToEntity(request);
    }
}
