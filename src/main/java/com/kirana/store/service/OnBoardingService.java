package com.kirana.store.service;

import com.kirana.store.collections.Store;
import com.kirana.store.responses.SuccessCreated;

import java.util.List;

public interface OnBoardingService {

    SuccessCreated save(Store store);

    List<Store> getStoreStartsWith(String name);

    boolean isStoreBelongToUser(String storeId, String userId);

    Store getByStoreId(String storeId);
}
