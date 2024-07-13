package com.kirana.store.middlewares;

import com.kirana.store.constants.ErrorCodes;
import com.kirana.store.errors.GenericErrorResponse;
import com.kirana.store.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerMiddleware {

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleException(NoCustomerRegisteredException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_CUSTOMERS_REGISTERED_FOR_STORE_CODE);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleException(NotEnoughStockException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_CUSTOMERS_REGISTERED_FOR_STORE_CODE);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    ResponseEntity<GenericErrorResponse> handleException(NoStoreRegistrationFoundException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_STORE_REGISTRATION_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<GenericErrorResponse> handleException(NoProductsFoundException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_PRODUCTS_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<GenericErrorResponse> handleException(NoPurchasesFoundException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_PURCHASE_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<GenericErrorResponse> handleException(NoSalesFoundException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.NO_SALES_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleException(Exception exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.GENERIC_ERROR_CODE);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleException(RuntimeException exc) {
        GenericErrorResponse error = new GenericErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exc.getMessage());
        error.setErrorCode(ErrorCodes.GENERIC_ERROR_CODE);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
