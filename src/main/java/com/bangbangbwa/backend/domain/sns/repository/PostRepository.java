package com.bangbangbwa.backend.domain.sns.repository;


import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepository {

  private final SqlSession mysql;

  public void savePost(Post post) { mysql.insert("PostMapper.save", post); }
}
