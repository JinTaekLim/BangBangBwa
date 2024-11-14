package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.entity.PostVisibilityMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostVisibilityMemberRepository {

  private final SqlSession mysql;

  public void publicMemberListSave(List<PostVisibilityMember> memberList) {
    mysql.insert("PostVisibilityMember.publicMemberSaveList", memberList);
  }

//  public void privateMemberListSave(List<PostVisibilityMember> memberList) {
//    mysql.insert("PostVisibilityMember.privateMemberSaveList", memberList);
//  }
}
