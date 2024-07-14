package com.kirana.store.entities;

import lombok.*;

import java.util.Date;
import java.util.HashMap;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionData {

    private boolean success;
    private String terms;
    private String privacy;
    private Long timestamp;
    private Date date;
    private String base;
    private HashMap<String, Double> rates;

}
