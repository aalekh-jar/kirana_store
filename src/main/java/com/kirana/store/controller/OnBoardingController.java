package com.kirana.store.controller;

import com.kirana.store.collections.Store;
import com.kirana.store.constants.ErrorCodes;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onboarding")
public class OnBoardingController {

    @Autowired
    OnBoardingService onBoardingService;

    @PostMapping
    public String save(@RequestBody Store store) {
        return onBoardingService.save(store);
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
