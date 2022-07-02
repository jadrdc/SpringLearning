package com.agustin.demo.service;

import com.agustin.demo.exceptions.AddressNotFoundException;
import com.agustin.demo.exceptions.PaymentMethodNotFoundException;
import com.agustin.demo.models.Address;
import com.agustin.demo.models.PaymentMethod;
import com.agustin.demo.repositories.AddressRepository;
import com.agustin.demo.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    @Transactional
    public Address updateAddress(Address address, Long id) {
        Address updateAddress = repository.findById(id)
                .map(address1 -> {
                    address1.setDescription(address.getDescription());
                    return repository.save(address1);
                })
                .orElseGet(() -> {
                    Address address1 = new Address();
                    address1.setDescription(address.getDescription());
                    return repository.save(address1);
                });
        return updateAddress;
    }

    public List<Address> findAll() {
        return repository.findAll();
    }


    public Address save(Address address) {
        return repository.save(address);
    }

    public Address findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
