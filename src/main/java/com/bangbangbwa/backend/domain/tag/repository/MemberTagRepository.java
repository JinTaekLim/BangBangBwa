package com.bangbangbwa.backend.domain.tag.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberTagRepository {

  private final SqlSession mysql;

  public void save(Long memberId, Long tagId) {
    Map<String, Object> params = new HashMap<>();
    params.put("memberId", memberId);
    params.put("tagId", tagId);
    mysql.insert("MemberTagMapper.save", params);
  }

  public void save(Long memberId, List<Long> tagIdList) {
    Map<String, Object> params = new HashMap<>();
    params.put("memberId", memberId);
    params.put("tagIdList", tagIdList);
    mysql.insert("MemberTagMapper.saveList", params);
  }

  public void delete(Long memberId, List<Long> tagIdList) {
    Map<String, Object> params = new HashMap<>();
    params.put("memberId", memberId);
    params.put("tagIdList", tagIdList);
    mysql.insert("MemberTagMapper.deleteList", params);
  }
}
