package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.member.common.enums.Interest;
import java.util.List;
import lombok.Getter;

@Getter
public class ProfileDto {

  private Long memberId;
  private String name;
  private String email;
  private String profileUrl;
  private String nickName;
  private String imageUrl;
  private String selfIntroduction;
  private List<Interest> interests;
}
