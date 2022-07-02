package com.agustin.demo.exceptions.custom;

import com.agustin.demo.exceptions.AddressNotFoundException;
import com.agustin.demo.exceptions.PaymentMethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentMethodNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PaymentMethodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String paymentMethodNotFoundHandler(PaymentMethodNotFoundException ex) {
        return ex.getMessage();
    }
}
