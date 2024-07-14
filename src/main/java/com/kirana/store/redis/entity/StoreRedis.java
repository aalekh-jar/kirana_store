package com.kirana.store.redis.entity;

import com.kirana.store.collections.Store;
import com.kirana.store.collections.common.Address;
import com.kirana.store.collections.common.OwnerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@ToString
@RedisHash("Store")
public class StoreRedis {
    @Id
    private String id;
    private String description;
    private OwnerDetails ownerDetails;
    private Address ownerAddress;
    private String userId;

    public Store mapToStore() {
        return new Store(id, description, ownerDetails, ownerAddress, userId);
    }
}
