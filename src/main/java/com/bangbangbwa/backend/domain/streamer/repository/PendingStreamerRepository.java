package com.bangbangbwa.backend.domain.streamer.repository;

import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PendingStreamerRepository {

  private final SqlSession mysql;

  public void save(PendingStreamer pendingStreamer) {
    mysql.insert("PendingStreamerMapper.save", pendingStreamer);
  }

}
