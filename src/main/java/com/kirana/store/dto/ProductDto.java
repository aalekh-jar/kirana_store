package com.kirana.store.dto;

import com.kirana.store.collections.Product;
import com.kirana.store.exceptions.DataValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private String vendor;
    private String itemType;
    private String location;
    private String unit;
    private String storeId;

    public boolean isValid() {
        if (name == null || name.isEmpty()
                || description == null || description.isEmpty() ||
                vendor == null || vendor.isEmpty() ||
                itemType == null || itemType.isEmpty() ||
                location == null || location.isEmpty() ||
                unit == null || unit.isEmpty() ||
                storeId == null || storeId.isEmpty()
        ) {
            throw new DataValidationError("Input data is wrong or missing");
        }
        return true;
    }

    public Product mapToProduct() {
        return new Product(
                name,
                vendor,
                itemType,
                description,
                unit,
                storeId,
                location
        );
    }
}
