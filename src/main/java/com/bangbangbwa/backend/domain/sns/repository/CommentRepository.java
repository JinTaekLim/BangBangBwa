package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

  private final SqlSession mysql;

  public void save(Comment comment) { mysql.insert("CommentMapper.save", comment); }

  public Optional<Comment> findByPostAndMember(Long postId, Long memberId) {
    Map<String, Object> params = new HashMap<>();
    params.put("postId", postId);
    params.put("memberId", memberId);
    return Optional.ofNullable(mysql.selectOne("CommentMapper.findByPostAndMember", params));
  }
}
