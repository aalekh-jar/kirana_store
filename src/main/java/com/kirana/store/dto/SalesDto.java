package com.kirana.store.dto;

import com.kirana.store.exceptions.DataValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {
    public String productId;
    public Float pricePerUnit;
    public Integer quantity;
    public String customerId;

    public boolean isValid() {
        if(productId == null || productId.isEmpty()) {
            throw new DataValidationError("No Product ID found");
        }
        if(pricePerUnit == null || pricePerUnit <= 0.0) {
            throw new DataValidationError("Price per unit should be greater than Zero");
        }
        if(quantity == null || quantity <= 0) {
            throw new DataValidationError("Quantity should be greater than 0");
        }
        if(customerId == null || customerId.isEmpty()) {
            throw new DataValidationError("Customer ID missing");
        }
        return true;
    }
}
