package com.bangbangbwa.backend.domain.streamer.common;

import com.bangbangbwa.backend.domain.post.common.entity.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class StreamerPostRecommender {

  private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

  // 각 게시물에 대한 확률 (팔로워 게시물, 랜덤)
  private static final int[][] PROBABILITIES = {
      {100, 0}, // 첫 번째 게시물
      {90, 10}, // 두 번째 게시물
      {80, 20}, // 세 번째 게시물
      {60, 40}, // 네 번째 게시물
      {50, 50}  // 다섯 번째 게시물
  };

  private static final int MAX_POSTS = 7;

  public List<Post> getPosts(
      List<Post> followerPosts, List<Post> randomPosts
  ) {
    List<Post> selectedPosts = new ArrayList<>();
    IntStream.range(0, MAX_POSTS)
        .mapToObj(i -> selectPost(i, followerPosts, randomPosts, selectedPosts))
        .filter(Objects::nonNull)
        .forEach(selectedPosts::add);
    return selectedPosts;
  }


  private Post selectPost(
      int num,
      List<Post> followerPosts,
      List<Post> randomPosts,
      List<Post> selectedPosts
  ) {
    int randomValue = RAND.nextInt(100);
    int[] currentProbabilities = PROBABILITIES[Math.min(num, PROBABILITIES.length - 1)];

    if (randomValue < currentProbabilities[0]) {
      return findUniquePost(followerPosts, randomPosts, selectedPosts);
    } else if (randomValue < currentProbabilities[0] + currentProbabilities[1]) {
      return findUniquePost(followerPosts, randomPosts, selectedPosts);
    } else {
      return findUniquePost(randomPosts, followerPosts, selectedPosts);
    }
  }

  private Post findUniquePost(
      List<Post> firstPosts,
      List<Post> secondPosts,
      List<Post> selectedPosts
  ) {
    return Stream.of(firstPosts, secondPosts)
        .flatMap(List::stream)
        .filter(post -> !selectedPosts.contains(post))
        .findFirst()
        .orElse(null);
  }
}
