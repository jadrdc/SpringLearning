package com.agustin.demo.controller;

import com.agustin.demo.models.PaymentMethod;
import com.agustin.demo.service.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class PaymentMethodController {
    private final PaymentMethodService service;

    PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @GetMapping("/paymentMethods")
    List<PaymentMethod> all() {
        return service.findAll();
    }

    @PostMapping("/paymentMethods")
    PaymentMethod newPaymentMethods(@RequestBody PaymentMethod paymentMethod) {
        return service.save(paymentMethod);
    }

    // Single item

    @GetMapping("/paymentMethods/{id}")
    PaymentMethod one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/paymentMethods/{id}")
    PaymentMethod replaceEmployee(@RequestBody PaymentMethod paymentMethod, @PathVariable Long id) {
        return service.updatePayment(paymentMethod, id);
    }

    @DeleteMapping("/paymentMethod/{id}")
    void deletePaymentMethod(@PathVariable Long id) {
        service.deleteById(id);
    }


}
