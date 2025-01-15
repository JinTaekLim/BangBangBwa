package com.bangbangbwa.backend.domain.sns.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class ReaderPostRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public ReaderPostRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static final String PREFIX = "read_post:";
    private static final TimeUnit EXPIRATION_UNIT = TimeUnit.HOURS;
    private static final long EXPIRATION_TIME = 30;

    public void addReadPost(String memberId, String postId) {
//        String key = PREFIX + memberId;
//        redisTemplate.opsForSet().add(key, postId);
//        redisTemplate.expire(key, EXPIRATION_TIME, EXPIRATION_UNIT);
    }

    public Set<String> findAllReadPostsByMemberId(String memberId) {
        String key = PREFIX + memberId;
        return redisTemplate.opsForSet().members(key);
    }
}
