package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostMediaRepository {

  private final SqlSession mysql;

  public void save(PostMedia postMedia) { mysql.insert("PostMediaMapper.save",postMedia);}
}
