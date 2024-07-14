package com.kirana.store.serviceImpl;

import com.kirana.store.collections.*;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.constants.SuccessCodes;
import com.kirana.store.constants.SuccessStrings;
import com.kirana.store.dto.SalesDto;
import com.kirana.store.exceptions.NotEnoughStockException;
import com.kirana.store.repository.*;
import com.kirana.store.responses.SuccessCreated;
import com.kirana.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Credit
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository salesRepository;

    @Autowired
    SaleDetailsRepository saleDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> save(SalesDto salesDto) {
        // Check the quantity
        Optional<Stock> stock = stockRepository.findByProductId(salesDto.productId);
        if (stock.isEmpty() || stock.get().getQuantity() < salesDto.quantity) {
            throw new NotEnoughStockException(ErrorStrings.NOT_ENOUGH_STOCK);
        }

        // Update quantity
        Stock updatedStock = stock.get();
        updatedStock.setQuantity(updatedStock.getQuantity() - salesDto.quantity);
        stockRepository.save(updatedStock);

        // Create Sale Entry
        Sale sale = new Sale(salesDto.customerId);
        String saleId = salesRepository.save(sale).getId();

        // Create SaleDetails Entry
        SaleDetails saleDetails = new SaleDetails(
                salesDto.productId,
                saleId,
                salesDto.pricePerUnit,
                salesDto.quantity
        );

        saleDetailsRepository.save(saleDetails);
        SuccessCreated response = new SuccessCreated();
        response.setCode(SuccessCodes.SUCCESS_CREATED);
        response.setMessage(SuccessStrings.SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<Sale> findAllSaleForCustomer(String customerId) {
        return salesRepository.findAllByCustomerId(customerId);
    }
}
