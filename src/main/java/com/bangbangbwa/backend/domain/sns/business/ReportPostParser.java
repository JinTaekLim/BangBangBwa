package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.dto.ReportPostDto;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.common.mapper.ReportPostMapper;
import org.springframework.stereotype.Component;

@Component
public class ReportPostParser {

    public ReportPost requestToEntity(ReportPostDto.Request request) {
        return ReportPostMapper.INSTANCE.dtoToEntity(request);
    }
}
