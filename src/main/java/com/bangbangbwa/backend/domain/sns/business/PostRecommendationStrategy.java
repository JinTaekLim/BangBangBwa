package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class PostRecommendationStrategy {

    private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

    // 각 게시물에 대한 확률 (팔로우한 사람, 비슷한 태그, 랜덤)
    private static final int[][] PROBABILITIES = {
        {70, 20, 10}, // 첫 번째 게시물
        {60, 25, 15}, // 두 번째 게시물
        {50, 30, 20}  // 이후 게시물
    };

    private static final int MAX_POSTS = 7;

    public List<Post> getPosts(
        List<Post> followPosts, List<Post> tagPosts, List<Post> randomPosts
    ) {
        List<Post> selectedPosts = new ArrayList<>();
        IntStream.range(0, MAX_POSTS)
            .mapToObj(i -> selectPost(i, followPosts, tagPosts, randomPosts, selectedPosts))
            .filter(Objects::nonNull)
            .forEach(selectedPosts::add);
        return selectedPosts;
    }


    private Post selectPost(
        int num,
        List<Post> followPosts,
        List<Post> tagPosts,
        List<Post> randomPosts,
        List<Post> selectedPosts
    ) {
        int randomValue = RAND.nextInt(100);
        int[] currentProbabilities = PROBABILITIES[Math.min(num, PROBABILITIES.length - 1)];

        if (randomValue < currentProbabilities[0]) {
            return findUniquePost(followPosts, tagPosts, randomPosts, selectedPosts);
        } else if (randomValue < currentProbabilities[0] + currentProbabilities[1]) {
            return findUniquePost(tagPosts, followPosts, randomPosts, selectedPosts);
        } else {
            return findUniquePost(randomPosts, followPosts, tagPosts, selectedPosts);
        }
    }

    private Post findUniquePost(
        List<Post> firstPosts,
        List<Post> secondPosts,
        List<Post> thirdPosts,
        List<Post> selectedPosts
    ) {
        return Stream.of(firstPosts, secondPosts, thirdPosts)
            .flatMap(List::stream)
            .filter(post -> !selectedPosts.contains(post))
            .findFirst()
            .orElse(null);
    }
}
