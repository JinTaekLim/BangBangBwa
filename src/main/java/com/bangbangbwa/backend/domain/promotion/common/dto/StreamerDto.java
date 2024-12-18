package com.bangbangbwa.backend.domain.promotion.common.dto;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StreamerDto {

  private Long id;
  private String name;
  private String todayComment;
  private String selfIntroduction;
  private String imageUrl;
  private List<TagDto> tags;
  private List<PlatformDto> platforms;

  public void updateTodayComment(String todayComment) {
    this.todayComment = todayComment;
  }
}
