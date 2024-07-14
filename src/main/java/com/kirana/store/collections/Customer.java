package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kirana.store.redis.entity.CustomerRedis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;


/**
 * This table will be used as end user also id will be
 * the primary key of the table
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private String storeId;

    public Customer(String name,
                    Integer age,
                    String phoneNumber,
                    String storeId) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.storeId = storeId;
    }

    public CustomerRedis mapToCache() {
        return new CustomerRedis(id, name, age, phoneNumber, storeId);
    }
}
