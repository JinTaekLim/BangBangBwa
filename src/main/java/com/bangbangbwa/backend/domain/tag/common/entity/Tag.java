package com.bangbangbwa.backend.domain.tag.common.entity;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tag {

  private Long id;
  private String name;
  private String createdId;
  private LocalDateTime createdAt;
  private String updatedId;
  private LocalDateTime updatedAt;

  @Builder
  public Tag(String name, String createdId) {
    this.name = name;

    LocalDateTime now = LocalDateTime.now();
    this.createdId = createdId;
    this.createdAt = now;
  }
}
