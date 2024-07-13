package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private String vendor;
    private String itemType;
    private String location;
    private String unit;
    private String storeId;

    public Product(String name,
                   String vendor,
                   String itemType,
                   String description,
                   String unit,
                   String storeId,
                   String location) {
        this.name = name;
        this.vendor = vendor;
        this.itemType = itemType;
        this.description = description;
        this.unit = unit;
        this.storeId = storeId;
        this.location = location;
    }
}
