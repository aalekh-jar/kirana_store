package com.kirana.store.redis.entity;

import com.kirana.store.collections.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@ToString
@RedisHash("Product")
public class ProductRedis {
    @Id
    private String id;
    private String name;
    private String description;
    private String vendor;
    private String itemType;
    private String location;
    private String unit;
    private String storeId;

    public Product mapToProduct() {
        return new Product(
                id,
                name,
                description,
                vendor,
                itemType,
                location,
                unit,
                storeId
                );
    }
}
