package com.bangbangbwa.backend.domain.member.repository;

import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

  public Optional<Member> findBySns(String snsId, SnsType snsType) {
    Map<String, Object> params = new HashMap<>();
    params.put("snsId", snsId);
    params.put("snsType", snsType);
    return Optional.ofNullable(mysql.selectOne("MemberMapper.findBySns", params));
  }

  public Optional<Member> findById(Long memberId) {
    return Optional.ofNullable(mysql.selectOne("MemberMapper.findById", memberId));
  }

  public boolean isExistsNickname(String nickname) {
    Member member = mysql.selectOne("MemberMapper.findByNickname", nickname);
    return Objects.nonNull(member);
  }

  public List<Member> findByNicknameContaining(String nickname) {
    return mysql.selectList("MemberMapper.findByNicknameContaining", nickname);
  }

  public Optional<ProfileDto> findProfile(ProfileDto profileDto) {
    return Optional.ofNullable(mysql.selectOne("MemberMapper.findProfile", profileDto));
  }

  public Optional<SummaryDto> findSummary(SummaryDto summaryDto) {
    return Optional.ofNullable(mysql.selectOne("MemberMapper.findSummary", summaryDto));
  }

  public void update(Member member) {
    mysql.update("MemberMapper.updateMember", member);
  }
}