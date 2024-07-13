package com.kirana.store.controller;

import com.kirana.store.entities.SaleData;
import com.kirana.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SaleService saleService;

    @PostMapping
    public ResponseEntity<?> test(@RequestBody SaleData saleData) {
        return saleService.save(saleData);
    }

}
