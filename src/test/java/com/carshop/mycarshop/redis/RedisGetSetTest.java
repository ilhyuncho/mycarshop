package com.carshop.mycarshop.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 로컬 Redis(localhost:6379) GET/SET 검증.
 * Redis 미기동 시 테스트는 skip 됩니다.
 */
class RedisGetSetTest {

    private static final String REDIS_HOST = "127.0.0.1";
    private static final int REDIS_PORT = 6379;

    private static LettuceConnectionFactory connectionFactory;
    private static StringRedisTemplate stringRedisTemplate;

    @BeforeAll
    static void connectToRedis() {
        Assumptions.assumeTrue(
                isRedisAvailable(),
                "localhost:" + REDIS_PORT + " 에 Redis가 실행 중이어야 합니다.");

        connectionFactory = new LettuceConnectionFactory(REDIS_HOST, REDIS_PORT);
        connectionFactory.afterPropertiesSet();

        stringRedisTemplate = new StringRedisTemplate(connectionFactory);
        stringRedisTemplate.afterPropertiesSet();
    }

    @AfterAll
    static void disconnect() {
        if (connectionFactory != null) {
            connectionFactory.destroy();
        }
    }

    @BeforeEach
    void clearTestKeys() {
        stringRedisTemplate.delete("test:hello");
        stringRedisTemplate.delete("test:missing");
    }

    @Test
    void setAndGet() {
        stringRedisTemplate.opsForValue().set("test:hello", "world");

        assertThat(stringRedisTemplate.opsForValue().get("test:hello")).isEqualTo("world");
    }

    @Test
    void setOverwritesPreviousValue() {
        stringRedisTemplate.opsForValue().set("test:hello", "first");
        stringRedisTemplate.opsForValue().set("test:hello", "second");

        assertThat(stringRedisTemplate.opsForValue().get("test:hello")).isEqualTo("second");
    }

    @Test
    void getReturnsNullWhenKeyMissing() {
        assertThat(stringRedisTemplate.opsForValue().get("test:missing")).isNull();
    }

    @Test
    void deleteRemovesKey() {
        stringRedisTemplate.opsForValue().set("test:hello", "world");

        Boolean deleted = stringRedisTemplate.delete("test:hello");

        assertThat(deleted).isTrue();
        assertThat(stringRedisTemplate.opsForValue().get("test:hello")).isNull();
    }

    private static boolean isRedisAvailable() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(REDIS_HOST, REDIS_PORT), 1000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
