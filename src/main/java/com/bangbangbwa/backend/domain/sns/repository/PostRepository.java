package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.sns.common.dto.GetLatestPostsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepository {

  private final SqlSession mysql;

  public void save(Post post) { mysql.insert("PostMapper.save", post); }

  public Optional<Post> findById(Long postId) {
    return Optional.ofNullable(mysql.selectOne("PostMapper.findById", postId));
  }

  public List<Post> findAllByPostType(PostType postType) {
    return mysql.selectList("PostMapper.findAllByPostType", postType);
  }

  public List<GetLatestPostsDto> findPostsWithinLast24Hours(PostType postType) {
    return mysql.selectList("PostMapper.findPostsWithinLast24Hours", postType);
  }

  public Optional<GetPostDetailsDto.Response> getPostDetails(Long postId, Long memberId) {
    Map<String, Object> params = new HashMap<>();
    params.put("postId", postId);
    params.put("memberId", memberId);
    return Optional.ofNullable(mysql.selectOne("PostMapper.getPostDetails", params));
  }
}
