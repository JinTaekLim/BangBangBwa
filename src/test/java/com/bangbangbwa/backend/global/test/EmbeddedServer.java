package com.bangbangbwa.backend.global.test;

import redis.embedded.RedisServer;

import java.io.IOException;

public class EmbeddedServer {

    private static RedisServer redisServer;

    public static void startRedis(int port) throws IOException {
        if (redisServer == null || !redisServer.isActive()) {
            redisServer = new RedisServer(port);
            redisServer.start();
        }
    }

    public static void stopRedis() throws IOException {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
        }
    }
}
