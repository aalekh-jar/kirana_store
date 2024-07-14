package com.kirana.store.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kirana.store.collections.common.Address;
import com.kirana.store.collections.common.OwnerDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Primary Key is ID which will be used as a foreign key in products
 * This Table will store the store owner details i.e tagging store
 */
@Data
@Document(collection = "store")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Store {

    @Id
    private String id;
    private String description;
    private OwnerDetails ownerDetails;
    private Address ownerAddress;
    private String userId;

    public Store(String description,
                 OwnerDetails ownerDetails,
                 Address ownerAddress,
                 String userId) {
        this.description = description;
        this.ownerDetails = ownerDetails;
        this.ownerAddress = ownerAddress;
        this.userId = userId;
    }
}
