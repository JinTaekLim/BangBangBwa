package com.bangbangbwa.backend.domain.streamer.repository;

import com.bangbangbwa.backend.domain.post.common.dto.MyPostDto.MyPostResponseReadStreamerInfo;
import com.bangbangbwa.backend.domain.streamer.common.entity.PostViewStreamer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostViewStreamerRepository {

  private final SqlSession mysql;

  public void save(PostViewStreamer postViewStreamer) {
    mysql.insert("PostViewStreamerMapper.save", postViewStreamer);
  }

  public List<MyPostResponseReadStreamerInfo> findStreamersByPostId(Long postId) {
    return mysql.selectList("PostViewStreamerMapper.findStreamersByPostId", postId);
  }
}
