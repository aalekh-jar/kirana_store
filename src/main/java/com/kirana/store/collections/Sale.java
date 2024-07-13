package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This will key track of the sales of the user
 * Which will map to the sales in the sales details
 */
@Data
@Document(collection = "sales")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sale {

    @Id
    private String id;
    private Long timeStamp;
    private String customerId;

    public Sale(String customerId) {
        this.timeStamp = System.currentTimeMillis();
        this.customerId = customerId;
    }

    public void setCurrentTimeStamp() {
        this.timeStamp = System.currentTimeMillis();
    }
}
