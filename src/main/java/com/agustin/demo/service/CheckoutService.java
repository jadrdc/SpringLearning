package com.agustin.demo.service;

import com.agustin.demo.exceptions.CheckoutNotFoundException;
import com.agustin.demo.models.*;
import com.agustin.demo.repositories.*;
import com.agustin.demo.request.CheckoutAddAddressRequest;
import com.agustin.demo.request.CheckoutAddPaymentRequest;
import com.agustin.demo.request.CheckoutAddProductRequest;
import com.agustin.demo.response.CheckoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CheckoutRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final PaymentRepository paymentRepository;


    public CheckoutResponse castCheckoutResponse(Checkout checkout) {
        CheckoutResponse response = new CheckoutResponse();
        double total = 0.0;
        for (Product p : checkout.getProductList()) {
            total = total + (p.getQuantity() * p.getPrice());
        }
        response.setId(checkout.getId());
        response.setUserName(checkout.getUser().getName() + " " + checkout.getUser().getLastName());
        response.setProducts(checkout.getProductList());
        response.setTotal(total);
        if (checkout.getPaymentMethod() != null) {
            Optional<PaymentMethod> paymentMethod = paymentRepository.findById(checkout.getPaymentMethod().getId());
            if (paymentMethod.isPresent()) {
                PaymentMethod payment = paymentMethod.get();
                response.setPayment(payment.getDescription());
            }
        }
        if (checkout.getAddress() != null) {
            Optional<Address> addressOptional = addressRepository.findById(checkout.getAddress().getId());
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                response.setAddress(address.getDescription());
            }
        }

        return response;
    }

    @Transactional
    public CheckoutResponse getCheckout(Long id) {
        CheckoutResponse response = new CheckoutResponse();
        Optional<Checkout> checkoutOptional = repository.findById(id);
        if (checkoutOptional.isPresent()) {
            Checkout checkout = checkoutOptional.get();
            response = castCheckoutResponse(checkout);
        }
        return response;
    }


    @Transactional
    public List<CheckoutResponse> getCheckoutList() {
        List<CheckoutResponse> list = new ArrayList<>();
        for (Checkout checkout : repository.findAll()) {
            CheckoutResponse response = castCheckoutResponse(checkout);
            list.add(response);
        }
        return list;
    }

    @Transactional
    public CheckoutResponse addProductCheckout(CheckoutAddProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        User finalUser = null;

        if (optionalUser.isPresent()) {
            finalUser = optionalUser.get();
        }
        User finalUser1 = finalUser;
        Checkout checkout = repository.findById(request.getCheckoutId()).map(checkout1 -> {
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        product.setQuantity((int) (product.getQuantity() + request.getQuantity()));
                        productRepository.save(product);
                    }
                    return repository.save(checkout1);
                })
                .orElseGet(() -> {
                    Checkout checkout1 = new Checkout();
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        product.setQuantity((int) request.getQuantity());
                        List<Product> productList = new ArrayList<>();
                        productList.add(product);
                        productRepository.save(product);
                        checkout1.setProductList(productList);
                    }
                    if (finalUser1 != null) {
                        checkout1.setUser(finalUser1);
                    }
                    return repository.save(checkout1);
                });
        CheckoutResponse response = castCheckoutResponse(checkout);
        return response;
    }


    @Transactional
    public CheckoutResponse removeProduct(CheckoutAddProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        Checkout checkout = repository.findById(request.getCheckoutId()).map(checkout1 -> {
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        if (product.getQuantity() - request.getQuantity() <= 0) {
                            checkout1.getProductList().remove(product);
                        } else {
                            product.setQuantity((int) (product.getQuantity() - request.getQuantity()));
                        }
                        productRepository.save(product);
                    }
                    return repository.save(checkout1);
                })
                .orElseThrow(() -> new CheckoutNotFoundException(request.getCheckoutId()));

        CheckoutResponse response = castCheckoutResponse(checkout);
        if (checkout.getProductList().size() == 0) {
            repository.delete(checkout);
        }
        return response;
    }

    @Transactional
    public CheckoutResponse getCheckout(long id) {
        Checkout checkout = repository.findById(id).map(checkout1 -> {
                    Optional<PaymentMethod> paymentMethod = paymentRepository.findById(checkout1.getPaymentMethod().getId());
                    Optional<Address> addressOptional = addressRepository.findById(checkout1.getAddress().getId());
                    return repository.save(checkout1);
                })
                .orElseThrow(() -> new CheckoutNotFoundException(id));
        CheckoutResponse response = castCheckoutResponse(checkout);
        return response;
    }

    @Transactional
    public CheckoutResponse addPaymentCheckout(CheckoutAddPaymentRequest request) {
        Optional<PaymentMethod> paymentMethod = paymentRepository.findById(request.getPaymentId());
        Checkout checkout = repository.findById(request.getCheckoutId()).map(checkout1 -> {
                    if (paymentMethod.isPresent()) {
                        PaymentMethod payment = paymentMethod.get();
                        checkout1.setPaymentMethod(payment);
                    }
                    return repository.save(checkout1);
                })
                .orElseThrow(() -> new CheckoutNotFoundException(request.getCheckoutId()));
        CheckoutResponse response = castCheckoutResponse(checkout);
        return response;
    }

    @Transactional
    public CheckoutResponse addAddressCheckout(CheckoutAddAddressRequest request) {
        Optional<Address> addressOptional = addressRepository.findById(request.getAddressId());
        Checkout checkout = repository.findById(request.getCheckoutId()).map(checkout1 -> {
                    if (addressOptional.isPresent()) {
                        Address address = addressOptional.get();
                        checkout1.setAddress(address);
                    }
                    return repository.save(checkout1);
                })
                .orElseThrow(() -> new CheckoutNotFoundException(request.getCheckoutId()));

        CheckoutResponse response = castCheckoutResponse(checkout);

        return response;
    }

}
