package com.agustin.demo.service;

import com.agustin.demo.exceptions.AddressNotFoundException;
import com.agustin.demo.exceptions.OrderNotFoundException;
import com.agustin.demo.models.*;
import com.agustin.demo.repositories.AddressRepository;
import com.agustin.demo.repositories.CheckoutRepository;
import com.agustin.demo.repositories.OrderRepository;
import com.agustin.demo.request.CreateOrderRequest;
import com.agustin.demo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CheckoutRepository checkoutRepository;

    public OrderResponse findById(Long id) {
        OrderResponse response = new OrderResponse();
        Checkout checkout = checkoutRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        response.setUserName(checkout.getUser().getName() + " " + checkout.getUser().getLastName());
        response.setProducts(checkout.getProductList());
        response.setAddress(checkout.getAddress().getDescription());
        response.setPayment(checkout.getPaymentMethod().getDescription());
        double total = 0.0;
        for (Product p : checkout.getProductList()) {
            total = total + (p.getQuantity() * p.getPrice());
        }
        response.setTotal(total);

        return response;

    }

    public List<OrderResponse> getOrders() {
        List<OrderResponse> response = new ArrayList<>();
        for (Order order : repository.findAll()) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setUserName(order.getCheckout().getUser().getName() + " " + order.getCheckout().getUser().getLastName());
            orderResponse.setProducts(order.getCheckout().getProductList());
            orderResponse.setAddress(order.getCheckout().getAddress().getDescription());
            orderResponse.setPayment(order.getCheckout().getPaymentMethod().getDescription());
            double total = 0.0;
            for (Product p : order.getCheckout().getProductList()) {
                total = total + (p.getQuantity() * p.getPrice());
            }
            orderResponse.setTotal(total);
            response.add(orderResponse);
        }
        return response;
    }


    public OrderResponse save(CreateOrderRequest orderRequest) {
        OrderResponse response = new OrderResponse();
        Optional<Checkout> checkoutOptional = checkoutRepository.findById(orderRequest.getCheckoutId());
        if (checkoutOptional.isPresent()) {
            Order order = new Order();
            Checkout checkout = checkoutOptional.get();
            order.setCheckout(checkout);
            response.setUserName(checkout.getUser().getName() + " " + checkout.getUser().getLastName());
            response.setProducts(checkout.getProductList());
            response.setAddress(checkout.getAddress().getDescription());
            response.setPayment(checkout.getPaymentMethod().getDescription());
            double total = 0.0;
            for (Product p : checkout.getProductList()) {
                total = total + (p.getQuantity() * p.getPrice());
            }
            response.setTotal(total);
            repository.save(order);
        }
        return response;
    }


}
