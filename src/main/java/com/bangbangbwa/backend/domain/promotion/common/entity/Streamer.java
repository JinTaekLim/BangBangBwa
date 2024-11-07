package com.bangbangbwa.backend.domain.promotion.common.entity;

import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("streamer")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Streamer {

  private Long id;
  private String todayComment;
  private String selfIntroduction;
  private String imageUrl;
  private String name;
  private List<Tag> tags;
  private List<Platform> platforms;

  @Builder
  public Streamer(
      String todayComment,
      String selfIntroduction,
      String imageUrl,
      String name
  ) {
    this.todayComment = todayComment;
    this.selfIntroduction = selfIntroduction;
    this.imageUrl = imageUrl;
    this.name = name;
    this.tags = new ArrayList<>();
    this.platforms = new ArrayList<>();
  }
}
