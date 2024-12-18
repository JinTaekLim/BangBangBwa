package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberTagRelation {

  private final MemberRepository memberRepository;

  public void relation(Member member, TagDto tag) {
    memberRepository.save(member, tag);
  }
}
