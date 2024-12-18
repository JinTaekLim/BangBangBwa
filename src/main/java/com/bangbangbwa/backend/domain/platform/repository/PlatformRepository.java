package com.bangbangbwa.backend.domain.platform.repository;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlatformRepository {

  private final SqlSession mysql;

  public List<PlatformDto> findAll() {
    return mysql.selectList("PlatformMapper.findAll");
  }
}
