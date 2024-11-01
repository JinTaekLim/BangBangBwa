package com.bangbangbwa.backend.domain.promotion.repository;

import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StreamerRepository {

  private final SqlSession mysql;

  public List<StreamerVo> findAll() {
    return mysql.selectList("StreamerMapper.selectAll");
  }
}
