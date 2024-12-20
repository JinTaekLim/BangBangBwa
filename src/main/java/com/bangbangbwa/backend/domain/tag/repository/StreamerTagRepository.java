package com.bangbangbwa.backend.domain.tag.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StreamerTagRepository {

  private final SqlSession mysql;

  public void save(Long streamerId, Long tagId) {
    Map<String, Object> params = new HashMap<>();
    params.put("streamerId", streamerId);
    params.put("tagId", tagId);
    mysql.insert("StreamerTagMapper.save", params);
  }

  public void save(Long streamerId, List<Long> tagIdList) {
    Map<String, Object> params = new HashMap<>();
    params.put("streamerId", streamerId);
    params.put("tagIdList", tagIdList);
    mysql.insert("StreamerTagMapper.saveList", params);
  }

  public void delete(Long streamerId, List<Long> tagIdList) {
    Map<String, Object> params = new HashMap<>();
    params.put("streamerId", streamerId);
    params.put("tagIdList", tagIdList);
    mysql.delete("StreamerTagMapper.deleteList", params);
  }
}
