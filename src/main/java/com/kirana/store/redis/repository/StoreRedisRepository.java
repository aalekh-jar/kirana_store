package com.kirana.store.redis.repository;

import com.kirana.store.redis.entity.StoreRedis;
import org.springframework.data.repository.CrudRepository;

public interface StoreRedisRepository extends CrudRepository<StoreRedis, String> {
}
