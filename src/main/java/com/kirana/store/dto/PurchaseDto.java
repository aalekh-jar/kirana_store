package com.kirana.store.dto;

import com.kirana.store.collections.Purchase;
import com.kirana.store.exceptions.DataValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private String productId;
    private Float cost;
    private Integer quantity;

    public boolean isValid() {
        if (productId == null || productId.isEmpty()) {
            throw new DataValidationError("Product ID missing");
        }
        if (cost == null || cost <= 0.0) {
            throw new DataValidationError("Cost parameter is missing or has invalid value");
        }
        if (quantity == null || quantity <= 0) {
            throw new DataValidationError("Quantity is missing or has invalid value");
        }
        return true;
    }

    public Purchase mapToPurchase() {
        return new Purchase(
                quantity, cost, productId
        );
    }
}
