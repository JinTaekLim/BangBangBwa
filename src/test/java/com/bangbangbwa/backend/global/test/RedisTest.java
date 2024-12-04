package com.bangbangbwa.backend.global.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@ActiveProfiles("test")
@DataRedisTest
@TestPropertySource(properties = "logging.config=classpath:logback-spring-test.xml")
abstract public class RedisTest {


    @BeforeAll
    static void setUpAll() throws IOException {
        EmbeddedServer.startRedis();
    }

    @AfterAll
    static void tearDownAll() throws IOException {
        EmbeddedServer.stopRedis();
    }


}
