package com.kirana.store.dto;

import com.kirana.store.collections.Store;
import com.kirana.store.collections.common.Address;
import com.kirana.store.collections.common.OwnerDetails;
import com.kirana.store.exceptions.DataValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private String description;
    private OwnerDetails ownerDetails;
    private Address ownerAddress;

    public Boolean isValid() {
        if (description == null || description.isEmpty()) {
            throw new DataValidationError("Description Missing");
        }
        if (ownerDetails == null) {
            throw new DataValidationError("Owner Details Missing");
        }
        if (ownerAddress == null) {
            throw new DataValidationError("Address Missing");
        }
        return true;
    }

    public Store mapToStore(String userId) {
        return new Store(
                description,
                ownerDetails,
                ownerAddress,
                userId
        );
    }

}
