package com.bangbangbwa.backend.domain.platform.repository;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.platform.common.dto.StreamerPlatformDto;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlatformRepository {

  private final SqlSession mysql;

  public void saveStreamerPlatform(StreamerPlatformDto params) {
    mysql.insert("PlatformMapper.saveStreamerPlatform", params);
  }

  public void removeStreamerPlatform(StreamerPlatformDto params) {
    mysql.delete("PlatformMapper.removeStreamerPlatform", params);
  }

  public List<PlatformDto> findAll() {
    return mysql.selectList("PlatformMapper.findAll");
  }

  public boolean isExistsStreamerPlatform(Long streamerId, Long platformId) {
    HashMap<String, Object> params = new HashMap<>();
    params.put("streamerId", streamerId);
    params.put("platformId", platformId);
    return Optional.ofNullable(
        mysql.selectOne("PlatformMapper.findByStreamerIdAndPlatformId", params)
    ).isPresent();
  }
}
