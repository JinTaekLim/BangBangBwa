package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostProvider {

  // 게시물 반환 최대 갯수
  private final int POST_SIZE = 7;

  private final PostRecommendationStrategy postRecommendationStrategy;
  private final PostReader postReader;


  public List<Post> getRandomPost(PostType postType) {
    List<Post> postList = postReader.findPostsByPostTypeWithLimit(postType, POST_SIZE);
    Collections.shuffle(postList);
    return postList;
  }

  public List<Post> getMemberPersonalizedPosts(Long memberId, Set<String> readPostIds) {
    PostType postType = PostType.STREAMER;
    List<Long> memberIds = new ArrayList<>();
    // 해당 유저가 갖고 있는 tag ID 이 담겨있는 리스트
    List<Long> tagIds = new ArrayList<>();

    int[] postProbability = postRecommendationStrategy.getPostReturnCount(POST_SIZE);
    int followPostSize = postProbability[0];
    int tagPostSize = postProbability[1];

    List<Post> followPost = getPostsByMemberIds(followPostSize, memberIds, readPostIds);
    List<Post> tagPost = getTagPost(tagPostSize, tagIds, readPostIds);
    int randomPostSize = POST_SIZE - followPost.size() - tagPost.size();
    List<Post> randomPost = getRandomPost(postType, randomPostSize, readPostIds);

    return mergeUniquePosts(followPost, tagPost, randomPost);
  }

  private List<Post> getRandomPost(PostType postType, int randomPostSize, Set<String> readPostIds) {
    return postReader.findRandomPostsExcludingReadIds(postType,randomPostSize, readPostIds);
  }


  // note. 팔로우한 스트리머의 Id 값을 반환하는 작업 이후 추가
  private List<Post> getPostsByMemberIds(int followPostSize, List<Long> memberIds, Set<String> readPostIds) {
//    return postReader.findPostsByStreamerAndMemberIdsExcludingReadIds(followPostSize, memberIds, readPostIds);
    return new ArrayList<>();
  }

  // note. Tag 값이 동일한 스트리머의 MemberIds 를 반환하는 작업 이후 추가
  public List<Post> getTagPost(int tagPostSize, List<Long> tagIds, Set<String> readPostIds) {
//    return postReader.findPostsByStreamerTagsExcludingReadIds(tagPostSize, tagIds, readPostIds);
    return new ArrayList<>();
  }

  private List<Post> mergeUniquePosts(List<Post> followPost, List<Post> tagPost, List<Post> randomPost) {
    Set<Post> uniquePosts = new HashSet<>();
    uniquePosts.addAll(followPost);
    uniquePosts.addAll(tagPost);
    uniquePosts.addAll(randomPost);
    return new ArrayList<>(uniquePosts);
  }

}
