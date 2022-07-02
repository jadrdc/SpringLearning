package com.agustin.demo.controller;

import com.agustin.demo.models.Address;
import com.agustin.demo.models.PaymentMethod;
import com.agustin.demo.service.AddressService;
import com.agustin.demo.service.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class AddressController {
    private final AddressService service;

    AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/address")
    List<Address> all() {
        return service.findAll();
    }

    @PostMapping("/address")
    Address newAddress(@RequestBody Address address) {
        return service.save(address);
    }

    @GetMapping("/address/{id}")
    Address one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/address/{id}")
    Address replaceAddress(@RequestBody Address  address, @PathVariable Long id) {
        return service.updateAddress(address, id);
    }
    @DeleteMapping("/address/{id}")
    void deleteAddress(@PathVariable Long id) {
        service.deleteById(id);
    }


}
