package com.kirana.store.dto;

import com.kirana.store.collections.Customer;
import com.kirana.store.exceptions.DataValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private Integer age;
    private String phoneNumber;
    private String storeId;

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            throw new DataValidationError("Name can not be empty");
        }
        if (age == null || age <= 0) {
            throw new DataValidationError("Age can not be empty or 0");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new DataValidationError("Phone no can not be empty");
        }
        if (storeId == null || storeId.isEmpty()) {
            throw new DataValidationError("Store id can not be empty");
        }
        return true;
    }

    public Customer mapToCustomer() {
        return new Customer(
                name,
                age,
                phoneNumber,
                storeId
        );
    }
}
