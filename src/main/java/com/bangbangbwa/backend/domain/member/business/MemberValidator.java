package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.member.exception.DuplicatedNicknameException;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.exception.NoPostPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

  private final MemberRepository memberRepository;
  private final MemberProvider memberProvider;

  public void validateNicknameDuplication(String nickname) {
    boolean isExists = memberRepository.isExistsNickname(nickname);
    if (isExists) {
      throw new DuplicatedNicknameException();
    }
  }

  public void validateRole(Role memberType, PostType snsType) {
    boolean hasPermission = (snsType == PostType.MEMBER && memberType == Role.MEMBER) ||
        (snsType == PostType.STREAMER && (memberType == Role.STREAMER || memberType == Role.ADMIN));
    if (!hasPermission) {
      throw new NoPostPermissionException();
    }
  }

  public boolean isMyMemberId(Long memberId) {
    try {
      Long myMemberId = memberProvider.getCurrentMemberId();
      return memberId.equals(myMemberId);
    } catch (Exception e) {
      return false;
    }
  }

  // 다른 사용자를 조회할 경우 불필요한 정보는 제거한다.
  public void removeData(SummaryDto dto) {
    dto.setFollowingCount(0L);
    dto.setIsSubmittedToStreamer(false);
  }
}