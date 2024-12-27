package com.bangbangbwa.backend.domain.platform.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StreamerPlatformRepository {

  private final SqlSession mysql;

  public void deleteByStreamerId(Long streamerId) {
    mysql.delete("StreamerPlatformMapper.deleteByStreamerId", streamerId);
  }
}
