package com.bangbangbwa.backend.domain.streamer.repository;

import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StreamerTagRepository {

  private final SqlSession mysql;

  public void save(Long streamerId, Tag tag) {
    Map<String, Object> params = new HashMap<>();
    params.put("streamerId", streamerId);
    params.put("tagId", tag.getId());
    mysql.insert("StreamerTagMapper.save", params);
  }
}
