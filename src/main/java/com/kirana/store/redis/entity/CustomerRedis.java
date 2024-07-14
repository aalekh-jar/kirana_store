package com.kirana.store.redis.entity;

import com.kirana.store.collections.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * This table will be used as end user also id will be
 * the primary key of the table
 */
@Data
@AllArgsConstructor
@ToString
@RedisHash("Customer")
public class CustomerRedis {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private String storeId;

    public Customer mapToCustomer() {
        return new Customer(id, name, age, phoneNumber, storeId);
    }

}
