package com.bangbangbwa.backend.domain.streamer.repository;

import com.bangbangbwa.backend.domain.streamer.common.entity.PostViewStreamer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostViewStreamerRepository {

  private final SqlSession mysql;

  public void save(PostViewStreamer postViewStreamer) {
    mysql.insert("PostViewStreamerMapper.save", postViewStreamer);
  }

}
