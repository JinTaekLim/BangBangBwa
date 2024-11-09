package com.bangbangbwa.backend.domain.sns.common.entity;

import com.bangbangbwa.backend.domain.sns.common.enums.PostStatus;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  private final String SELF = "SELF";

  private Long id;
  private PostType postType;
  private PostStatus status;
  private Long memberId;
  private String title;
  private String content;
  private String createdId;
  private List<Long> publicMembers;
  private List<Long> privateMembers;
  private LocalDateTime createdAt;
  private String updatedId;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;


  @Builder
  public Post(PostType postType, Long memberId, String title, String content,
      List<Long> publicMembers, List<Long> privateMembers) {
    this.status = PostStatus.DRAFT;
    this.postType = postType;
    this.memberId = memberId;
    this.title = title;
    this.content = content;
    this.publicMembers = publicMembers;
    this.privateMembers = privateMembers;
    this.createdId = SELF;
    this.createdAt = LocalDateTime.now();
  }

  public void updateMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void updateId(Long id) {
    this.id = id;
  }

  public void updatedStatus(PostStatus status) {
    this.status = status;
  }

}
