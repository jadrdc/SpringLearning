package com.agustin.demo.service;

import com.agustin.demo.exceptions.AddressNotFoundException;
import com.agustin.demo.exceptions.ProductNotFoundException;
import com.agustin.demo.models.Address;
import com.agustin.demo.models.Product;
import com.agustin.demo.repositories.AddressRepository;
import com.agustin.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional
    public Product updateProduct(Product product, Long id) {
        Product updateProduct = repository.findById(id).map(product1 -> {
                    product1.setName(product.getName());
                    product1.setPrice(product.getPrice());
                    return repository.save(product1);
                })
                .orElseGet(() -> {
                    Product product1 = new Product();
                    product1.setName(product.getName());
                    product1.setPrice(product.getPrice());
                    return repository.save(product1);
                });
        return updateProduct;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }


    public Product save(Product product) {
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
