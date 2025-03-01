package com.bangbangbwa.backend.domain.streamer.repository;


import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DailyMessageRepository {

  private final RedisTemplate<String, String> redisTemplate;

  public DailyMessageRepository(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public static final String PREFIX = "dailyMessage:";
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

  public List<DailyMessage> findByIds(List<Long> ids) {
    List<String> messages = redisTemplate.opsForValue().multiGet(ids.stream()
        .map(id -> PREFIX + id)
        .collect(Collectors.toList()));

    return IntStream.range(0, ids.size())
        .mapToObj(i -> DailyMessage.builder()
            .memberId(ids.get(i))
            .message(messages != null ? messages.get(i) : null)
            .build())
        .collect(Collectors.toList());
  }

  public String getDailyMessage(Long memberId) {
    String key = PREFIX + memberId;
    return redisTemplate.opsForValue().get(key);
  }
}
