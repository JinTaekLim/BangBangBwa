package com.bangbangbwa.backend.domain.member.repository;

import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepository {

  private final SqlSession mysql;

  public void save(Follow follow) {
    mysql.insert("FollowMapper.save", follow);
  }

}
