package com.bangbangbwa.backend.domain.promotion.repository;

import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StreamerRepository {

  private final SqlSession mysql;

  public List<Streamer> findAll() {
    return mysql.selectList("StreamerMapper.selectAll");
  }

  public Optional<Streamer> findByMemberId(Long memberId) {
    return Optional.of(mysql.selectOne("StreamerMapper.findByMemberId", memberId));
  }

  public void save(Streamer streamer) {

  }
}
