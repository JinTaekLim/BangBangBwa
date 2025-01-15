package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import com.bangbangbwa.backend.domain.post.common.enums.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportCommentRepository {

    private final SqlSession mysql;

    public void save(ReportComment reportComment) {
        mysql.insert("ReportCommentMapper.save", reportComment);
    }

    public Optional<ReportComment> findByCommentIdAndCreatedId(Long commentId, String createdId, ReportStatus status) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("createdId", createdId);
        params.put("status", status);
        return Optional.ofNullable(
                mysql.selectOne("ReportCommentMapper.findByCommentIdAndCreatedIdAndStatus", params)
        );
    }
}
