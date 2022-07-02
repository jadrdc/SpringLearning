package com.agustin.demo.controller;

import com.agustin.demo.models.Address;
import com.agustin.demo.models.Product;
import com.agustin.demo.models.User;
import com.agustin.demo.service.AddressService;
import com.agustin.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ProductController {
    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product")
    List<Product> all() {
        return service.findAll();
    }

    @PostMapping("/product")
    Product newProduct(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/product/{id}")
    Product replaceProduct(@RequestBody Product product, @PathVariable Long id) {
        return service.updateProduct(product, id);
    }
    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }


}
