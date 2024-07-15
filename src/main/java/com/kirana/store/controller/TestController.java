package com.kirana.store.controller;

import com.kirana.store.constants.Constants;
import com.kirana.store.redis.config.RedisDistributedLock;
import com.kirana.store.service.CurrencyConversionService;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/store/test")
public class TestController {

    @Autowired
    CurrencyConversionService service;

    @Autowired
    OnBoardingService onBoardingService;

    @Autowired
    RedisDistributedLock redisDistributedLock;

    @GetMapping
    public ResponseEntity<?> test(@RequestHeader(Constants.X_USER_ID) String auth) {
//        studentRepository.save(new Student(
//                "aalekh",
//                "aalekh sajonia",
//                Student.Gender.MALE,
//                10
//        ));
        try {
            boolean isLocked = redisDistributedLock.acquireLock("test_lock_key", 10000, TimeUnit.MILLISECONDS);
            if(!isLocked) {
                return new ResponseEntity<>("Resource already locked", HttpStatus.OK);
            } else {
                HashMap<String, Double> data = service.getCurrencyRateNow();
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            redisDistributedLock.releaseLock("test_lock_key");
        }
    }

}
