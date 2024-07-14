package com.kirana.store.controller;

import com.kirana.store.constants.Constants;
import com.kirana.store.service.CurrencyConversionService;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/store/test")
public class TestController {

    @Autowired
    CurrencyConversionService service;

    @Autowired
    OnBoardingService onBoardingService;

    @GetMapping
    public ResponseEntity<?> test(@RequestHeader(Constants.X_USER_ID) String auth) {
        boolean isTrue = onBoardingService.isStoreBelongToUser("6693ad62604c8e609b2095bf" ,auth);
        System.out.println("store belonging: " + isTrue);
        HashMap<String, Double> data = service.getCurrencyRateNow();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
