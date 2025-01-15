package com.bangbangbwa.backend.global.test;

import redis.embedded.RedisServer;

import java.io.IOException;

public class EmbeddedServer {

    private static RedisServer redisServer;

    private final static int REDIS_PORT = 6379;

    public static void startRedis() throws IOException {
        if (redisServer == null || !redisServer.isActive()) {
            redisServer = new RedisServer(REDIS_PORT);
            redisServer.start();
        }
    }

    public static void stopRedis() throws IOException {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
        }
    }
}
