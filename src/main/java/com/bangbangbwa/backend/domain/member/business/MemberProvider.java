package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.exception.AuthenticationNameNullException;
import com.bangbangbwa.backend.domain.member.exception.AuthenticationNullException;
import com.bangbangbwa.backend.domain.member.exception.NotParsedValueException;
import com.bangbangbwa.backend.domain.member.exception.type.MemberErrorType;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MemberProvider {

  public Long getCurrentMemberId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.isNull(authentication)) {
      throw new AuthenticationNullException();
    }
    String name = authentication.getName();
    if (!StringUtils.hasText(name)) {
      throw new AuthenticationNameNullException();
    }
    try {
      return Long.valueOf(name);
    } catch (NumberFormatException e) {
      throw new NotParsedValueException(
          MemberErrorType.NOT_PARSED_VALUE_ERROR.getMessage() + " : " + name);
    }
  }
}