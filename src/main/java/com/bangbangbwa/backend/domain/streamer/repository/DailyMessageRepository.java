package com.bangbangbwa.backend.domain.streamer.repository;


import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DailyMessageRepository {

  private final RedisTemplate<String, String> redisTemplate;

  public DailyMessageRepository(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  private static final String PREFIX = "dailyMessage:";
  private static final TimeUnit EXPIRATION_UNIT = TimeUnit.HOURS;




  public void save(Long id, String message, long expirationTime) {
    String key = PREFIX + id;
    redisTemplate.opsForValue().set(
        key,
        message,
        expirationTime,
        EXPIRATION_UNIT
    );
  }
}
