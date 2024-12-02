package com.bangbangbwa.backend.domain.promotion.common.entity;

import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Alias("streamer")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Streamer {

  @PostConstruct
  private void init() {
    tags = new ArrayList<>();
    platforms = new ArrayList<>();
  }
  
  private Long id;
  private List<Tag> tags;
  private List<Platform> platforms;
}
