package com.kirana.store.service;

import com.kirana.store.collections.Sale;
import com.kirana.store.dto.SalesDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaleService {

    ResponseEntity<?> save(SalesDto salesDto);

    List<Sale> findAllSaleForCustomer(String customerId);
}
