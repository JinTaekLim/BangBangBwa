package com.bangbangbwa.backend.domain.member.repository;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowResponse;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import java.util.List;
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

  public List<FollowerResponse> findFollowersByMemberId(Long memberId) {
    return mysql.selectList("FollowMapper.findFollowersByMemberId", memberId);
  }

  public List<FollowResponse> findFollowsByMemberId(Long memberId) {
    return mysql.selectList("FollowMapper.findFollowsByMemberId", memberId);
  }

}
