package com.kirana.store.controller;

import com.kirana.store.collections.Store;
import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorCodes;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.dto.StoreDto;
import com.kirana.store.exceptions.DataValidationError;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.responses.SuccessCreated;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/onboarding")
public class OnBoardingController {

    @Autowired
    OnBoardingService onBoardingService;

    @PostMapping
    public ResponseEntity<SuccessCreated> save(
            @RequestBody StoreDto storeDto,
            @RequestHeader(Constants.X_USER_ID) String userId) {
        if (userId == null || userId.isEmpty() || !storeDto.isValid()) {
            throw new DataValidationError("Input data not avalid");
        }
        return new ResponseEntity<>(onBoardingService.save(storeDto.mapToStore(userId)), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Store> getStoreList(@RequestParam String name) {
        List<Store> stores = onBoardingService.getStoreStartsWith(name);
        if (stores.isEmpty()) {
            throw new NoStoreRegistrationFoundException(ErrorStrings.NO_STORE_MATCHES_GIVEN_STRING);
        }
        return stores;
    }

}
