package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sales_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetails {

    @Id
    private String id;
    private String salesId; //FK
    private String productId; //FK
    private Float sellingPrice;
    private Integer quantity;

    public SaleDetails(String productId,
                       String salesId,
                       Float sellingPrice,
                       Integer quantity) {
        this.productId = productId;
        this.salesId = salesId;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }
}
