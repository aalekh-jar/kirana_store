package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * This table will be used as end user also id will be
 * the primary key of the table
 */
@Data
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
}
