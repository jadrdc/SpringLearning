package com.agustin.demo.exceptions.custom;

import com.agustin.demo.exceptions.AddressNotFoundException;
import com.agustin.demo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AddressNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String addressNotFoundHandler(AddressNotFoundException ex) {
        return ex.getMessage();
    }
}
