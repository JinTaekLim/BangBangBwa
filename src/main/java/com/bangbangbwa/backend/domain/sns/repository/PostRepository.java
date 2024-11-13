package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepository {

  private final SqlSession mysql;

  public void save(Post post) { mysql.insert("PostMapper.save", post); }

  public Optional<Post> findById(Long postId) {
    return Optional.ofNullable(mysql.selectOne("PostMapper.findById", postId));
  }
}
