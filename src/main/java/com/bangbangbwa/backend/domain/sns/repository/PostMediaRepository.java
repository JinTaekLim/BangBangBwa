package com.bangbangbwa.backend.domain.sns.repository;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostMediaRepository {

  private final RedisTemplate<String, String> redisTemplate;

  public PostMediaRepository(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  private static final String PREFIX = "media:";
  private static final TimeUnit EXPIRATION_UNIT = TimeUnit.HOURS;




  public void save(String url, Long memberId, long expirationTime) {
    String key = PREFIX + url;
    redisTemplate.opsForValue().set(
        key,
        memberId.toString(),
        expirationTime,
        EXPIRATION_UNIT
    );
  }
}
