package com.kirana.store.lock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class LockConfig {

    private static final String LOCK_NAME = "lock";

    @Bean(destroyMethod = "destroy")
    public RedisLockRegistry redisLockRegistry(
            JedisConnectionFactory jedisConnectionFactory
    ) {
        return new RedisLockRegistry(jedisConnectionFactory, LOCK_NAME);
    }

}
