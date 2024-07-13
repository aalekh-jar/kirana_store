package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This maintains all the debit transactions
@Data
@Document(collection = "purchase")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Purchase {

    @Id
    private String id;
    private String productId;
    private Float cost;
    private Long timeStamp;
    private Integer quantity;

    public Purchase(Integer quantity, Float cost, String productId) {
        this.quantity = quantity;
        this.cost = cost;
        this.productId = productId;
        this.timeStamp = System.currentTimeMillis();
    }

    public void setCurrentTimeStamp() {
        this.timeStamp = System.currentTimeMillis();
    }
}
