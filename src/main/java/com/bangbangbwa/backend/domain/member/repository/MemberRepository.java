package com.bangbangbwa.backend.domain.member.repository;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final SqlSession mysql;

  public void save(Member member) {
    mysql.insert("MemberMapper.save", member);
  }

  public Optional<Member> findById(Long memberId) {
    return Optional.ofNullable(mysql.selectOne("MemberMapper.findById", memberId));
  }
}
