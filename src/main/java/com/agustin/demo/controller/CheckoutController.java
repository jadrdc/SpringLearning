package com.agustin.demo.controller;

import com.agustin.demo.request.CheckoutAddAddressRequest;
import com.agustin.demo.request.CheckoutAddPaymentRequest;
import com.agustin.demo.request.CheckoutAddProductRequest;
import com.agustin.demo.response.CheckoutResponse;
import com.agustin.demo.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckoutController {

    private final CheckoutService service;

    CheckoutController(CheckoutService service) {
        this.service = service;
    }


    @PostMapping("/addcheckout")
    CheckoutResponse addProductCheckout(@RequestBody CheckoutAddProductRequest request) {
        return service.addProductCheckout(request);
    }

    @PostMapping("/removecheckout")
    CheckoutResponse removeProductCheckout(@RequestBody CheckoutAddProductRequest request) {
        return service.removeProduct(request);
    }

    @PostMapping("/addpaymentcheckout")
    CheckoutResponse addPaymentCheckout(@RequestBody CheckoutAddPaymentRequest request) {
        return service.addPaymentCheckout(request);
    }

    @PostMapping("/addaddresscheckout")
    CheckoutResponse addAddressCheckout(@RequestBody CheckoutAddAddressRequest request) {
        return service.addAddressCheckout(request);
    }

    @GetMapping("/getcheckout/{id}")
    CheckoutResponse getCheckout(@PathVariable Long id) {
        return service.getCheckout(id);
    }

    @GetMapping("/getcheckout")
    List<CheckoutResponse> getCheckoutList() {
        return service.getCheckoutList();
    }


    @GetMapping("/getcheckoutlist")
    List<CheckoutResponse> getCheckoutListT() {
        return service.getCheckoutList();
    }
}

