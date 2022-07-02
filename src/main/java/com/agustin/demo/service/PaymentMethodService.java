package com.agustin.demo.service;

import com.agustin.demo.exceptions.PaymentMethodNotFoundException;
import com.agustin.demo.models.PaymentMethod;
import com.agustin.demo.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final PaymentRepository repository;

    @Transactional
    public PaymentMethod updatePayment(PaymentMethod paymentMethod, Long id) {
        PaymentMethod updatePaymentMethod= repository.findById(id)
                .map(paymentMethod1 -> {
                    paymentMethod1.setDescription(paymentMethod.getDescription());
                    return repository.save(paymentMethod1);
                })
                .orElseGet(() -> {
                    PaymentMethod paymentMethod2 = new PaymentMethod();
                    paymentMethod2.setDescription(paymentMethod.getDescription());
                    return repository.save(paymentMethod2);
                });
        return updatePaymentMethod;
    }

    public List<PaymentMethod> findAll() {
        return repository.findAll();
    }


    public PaymentMethod save(PaymentMethod paymentMethod) {
        return repository.save(paymentMethod);
    }

    public PaymentMethod findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
