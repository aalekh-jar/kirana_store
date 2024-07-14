package com.kirana.store.serviceImpl;

import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.entities.CurrencyConversionData;
import com.kirana.store.exceptions.CurrentConversionDataNotAvailable;
import com.kirana.store.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public HashMap<String, Double> getCurrencyRateNow() {
        CurrencyConversionData response = restTemplate.getForObject(
                Constants.CURRENCY_CONVERSION_DETAILS_ENDPOINT, CurrencyConversionData.class);
        if(response == null || !response.isSuccess()) {
            throw new CurrentConversionDataNotAvailable(ErrorStrings.CURRENCY_CONVERSION_DATA_NOT_FOUND);
        }
        return response.getRates();
    }
}
