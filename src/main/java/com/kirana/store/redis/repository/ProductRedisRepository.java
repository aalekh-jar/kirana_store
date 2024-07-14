package com.kirana.store.redis.repository;

import com.kirana.store.redis.entity.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepository extends CrudRepository<ProductRedis, String> {
}
