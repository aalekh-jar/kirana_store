package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stock {

    @Id
    private String id;
    @Indexed(unique = true)
    private String productId;
    private Integer quantity;
    private Long ts;

    public Stock(String productId, Integer quantity, Long ts) {
        this.productId = productId;
        this.quantity = quantity;
        this.ts = ts;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
