package com.kirana.store.redis.repository;

import com.kirana.store.redis.entity.CustomerRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRedisRepository extends CrudRepository<CustomerRedis, String> {

}
