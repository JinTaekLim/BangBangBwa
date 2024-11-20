package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.enums.VisibilityType;
import com.bangbangbwa.backend.domain.sns.exception.InvalidMemberVisibilityException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidator {

  public VisibilityType validateMembers(List<Long> publicMembers, List<Long> privateMembers) {
    if (!publicMembers.isEmpty() && !privateMembers.isEmpty()) {
      throw new InvalidMemberVisibilityException();
    }
    if (publicMembers.isEmpty() && privateMembers.isEmpty()) return null;
    return !publicMembers.isEmpty() ? VisibilityType.PUBLIC : VisibilityType.PRIVATE;
  }


}
