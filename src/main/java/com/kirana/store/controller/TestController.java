package com.kirana.store.controller;

import com.kirana.store.service.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    private Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public ResponseEntity<?> test() {
//        log.info("Test called");
        HashMap<String, Double> data = service.getCurrencyRateNow();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
