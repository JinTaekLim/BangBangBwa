package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUpdater {

  private final MemberRepository memberRepository;

  public void updateMember(Member member) {
    if (Objects.nonNull(member)) {
      memberRepository.update(member);
    }
  }
}
