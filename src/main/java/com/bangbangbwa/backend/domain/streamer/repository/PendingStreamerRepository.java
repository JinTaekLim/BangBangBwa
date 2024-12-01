package com.bangbangbwa.backend.domain.streamer.repository;

import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.common.enums.PendingType;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PendingStreamerRepository {

  private final SqlSession mysql;

  public void save(PendingStreamer pendingStreamer) {
    mysql.insert("PendingStreamerMapper.save", pendingStreamer);
  }

  public void updateStatus(PendingStreamer pendingStreamer) {
    mysql.insert("PendingStreamerMapper.updateStatus",pendingStreamer);
  }

  public Optional<PendingStreamer> findByMemberIdAndStatus(Long memberId, PendingType status) {
    Map<String, Object> params = new HashMap<>();
    params.put("memberId", memberId);
    params.put("status", status);
    return Optional.ofNullable(mysql.selectOne("PendingStreamerMapper.findByMemberIdAndStatus", params));
  }
}
