package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.exception.MaxNicknameCountExceededException;
import com.bangbangbwa.backend.domain.member.exception.NegativeNicknameCountException;
import com.bangbangbwa.backend.domain.member.exception.NotExistsRemainNicknameException;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NicknameProvider {

  static final int ALLOWABLE_RANGE = 10;
  static final int MAX_REQUEST_COUNT = 100;

  private final MemberReader memberReader;
  private final NicknameGenerator nicknameGenerator;

  public Set<String> provideRandomNicknames(int count) {
    Set<String> nicknames = new HashSet<>();
    validateCount(count);
    int maxCount = count + ALLOWABLE_RANGE;
    for (int i = 0; i < maxCount && nicknames.size() < count; i++) {
      String nickname = nicknameGenerator.generate();
      if (!memberReader.existsByNickname(nickname)) {
        nicknames.add(nickname);
      }
    }

    if (nicknames.size() < count) {
      throw new NotExistsRemainNicknameException();
    }
    return nicknames;
  }

  private void validateCount(int count) {
    if (count > MAX_REQUEST_COUNT) {
      throw new MaxNicknameCountExceededException();
    } else if (count < 0) {
      throw new NegativeNicknameCountException();
    }
  }
}
