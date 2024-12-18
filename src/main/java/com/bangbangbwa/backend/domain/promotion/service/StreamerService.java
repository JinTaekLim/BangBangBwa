package com.bangbangbwa.backend.domain.promotion.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.post.business.PostProvider;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.promotion.business.RandomStreamerProvider;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.sns.business.ReaderPostReader;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamerService {

  private final RandomStreamerProvider randomStreamerProvider;
  private final MemberProvider memberProvider;
  private final PostProvider postProvider;
  private final ReaderPostReader readerPostReader;

  public Set<PromotionStreamerDto.PromotionStreamer> getRandomStreamers() {
    return randomStreamerProvider.getStreamers(memberProvider.getCurrentMemberIdOrNull());
  }

  public List<Post> getPostList() {
    Long memberId = memberProvider.getCurrentMemberId();
    Set<String> readPostIds = readerPostReader.findAllReadPostsByMemberId(memberId);
    return postProvider.getStreamerPersonalizedPosts(memberId, readPostIds);
  }
}
