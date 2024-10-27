package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.exception.type.DuplicatedNicknameException;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

  private final MemberRepository memberRepository;

  public void validateNicknameDuplication(String nickname) {
    boolean isExists = memberRepository.isExistsNickname(nickname);
    if (isExists) {
      throw new DuplicatedNicknameException();
    }
  }
}