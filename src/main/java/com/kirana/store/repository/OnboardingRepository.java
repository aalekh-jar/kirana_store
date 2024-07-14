package com.kirana.store.repository;

import com.kirana.store.collections.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnboardingRepository extends MongoRepository<Store, String> {

    List<Store> findByDescriptionStartsWith(String name);

    Store findByIdAndUserId(String id, String userId);

}
