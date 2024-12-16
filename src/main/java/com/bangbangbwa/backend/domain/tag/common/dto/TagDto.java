package com.bangbangbwa.backend.domain.tag.common.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TagDto {

  @EqualsAndHashCode.Include
  private Long id;
  @EqualsAndHashCode.Exclude
  private String name;
}
