package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.common.Interest;
import com.bangbangbwa.backend.domain.member.common.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.exception.InvalidInterestException;
import com.bangbangbwa.backend.domain.member.exception.InvalidSnsTypeException;
import com.bangbangbwa.backend.domain.oauth.common.SnsType;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MemberValidator {

  public void validate(MemberSignupDto.Request request, String snsType) {
    checkSnsType(snsType);
//    checkInterests(request.interests());
  }

  private void checkSnsType(String snsType) {
    if (!StringUtils.hasText(snsType) || Arrays.stream(SnsType.values())
        .noneMatch(type -> type.name().equals(snsType.toUpperCase()))) {
      throw new InvalidSnsTypeException();
    }
  }

  private void checkInterests(List<String> interests) {
    for (String interest : interests) {
      if (!StringUtils.hasText(interest) || Arrays.stream(Interest.values())
          .noneMatch(type -> type.name().equals(interest.toUpperCase()))) {
        throw new InvalidInterestException();
      }
    }
  }
}
