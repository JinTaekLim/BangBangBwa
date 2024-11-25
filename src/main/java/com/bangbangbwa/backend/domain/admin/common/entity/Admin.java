package com.bangbangbwa.backend.domain.admin.common.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Alias("admin")
@Getter
@AllArgsConstructor
public class Admin {

  private final String SYSTEM = "SYSTEM";

  private Long id;
  private String createdId;
  private LocalDateTime createdAt;

  @Builder
  public Admin() {
    this.createdId = SYSTEM;
    this.createdAt = LocalDateTime.now();
  }
}
