package com.agustin.demo.controller;

import com.agustin.demo.request.CreateOrderRequest;
import com.agustin.demo.response.OrderResponse;
import com.agustin.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }



    @PostMapping("/order")
    OrderResponse addOrder(@RequestBody CreateOrderRequest request) {
        return service.save(request);
    }

    @GetMapping("/order/{id}")
    OrderResponse getCheckout(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/orders")
    List<OrderResponse> getCheckoutList() {
        return service.getOrders();
    }


}
