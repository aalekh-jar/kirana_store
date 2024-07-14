package com.kirana.store.service;

import com.kirana.store.collections.Sale;
import com.kirana.store.dto.SalesDto;
import com.kirana.store.responses.SuccessCreated;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaleService {

    SuccessCreated save(SalesDto salesDto);

    List<Sale> findAllSaleForCustomer(String customerId);
}
