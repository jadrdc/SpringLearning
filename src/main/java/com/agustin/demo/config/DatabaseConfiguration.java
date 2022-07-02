package com.agustin.demo.config;

import com.agustin.demo.models.Address;
import com.agustin.demo.models.PaymentMethod;
import com.agustin.demo.models.Product;
import com.agustin.demo.models.User;
import com.agustin.demo.repositories.AddressRepository;
import com.agustin.demo.repositories.PaymentRepository;
import com.agustin.demo.repositories.ProductRepository;
import com.agustin.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ProductRepository productRepository,
                                   AddressRepository addressRepository,
                                   PaymentRepository paymentRepository) {
        User user = new User("Agustin", "Reinoso", "1234");
        User user2 = new User("Aluis", "Foo", "234");
        User user3 = new User("Luis", "Guzman", "345");
        User user4 = new User("Mirla", "Rio", "2345");
        Product product = new Product("Jugo", 15.0);
        Product product2 = new Product("Pan", 45.0);
        Product product3 = new Product("Chocolate", 200.0);
        Product product4 = new Product("Pollo", 500.0);
        PaymentMethod method = new PaymentMethod("Visa");
        PaymentMethod method2 = new PaymentMethod("Mastercard");
        PaymentMethod method3 = new PaymentMethod("Cash");
        PaymentMethod method4 = new PaymentMethod("Amex");
        Address address = new Address("Avenida Independencia 1103");
        Address address2 = new Address("Avenida Winston Churchill 12");
        Address address3 = new Address("Avenida Nunez de Caceres 5");
        Address address4 = new Address("Avenida Ecologica");


        return args -> {
            log.info("Preloading " + userRepository.save(user));
            log.info("Preloading " + userRepository.save(user2));
            log.info("Preloading " + userRepository.save(user3));
            log.info("Preloading " + userRepository.save(user4));
            log.info("Preloading " + productRepository.save(product));
            log.info("Preloading " + productRepository.save(product2));
            log.info("Preloading " + productRepository.save(product3));
            log.info("Preloading " + productRepository.save(product4));
            log.info("Preloading " + paymentRepository.save(method));
            log.info("Preloading " + paymentRepository.save(method2));
            log.info("Preloading " + paymentRepository.save(method3));
            log.info("Preloading " + paymentRepository.save(method4));
            log.info("Preloading " + addressRepository.save(address));
            log.info("Preloading " + addressRepository.save(address2));
            log.info("Preloading " + addressRepository.save(address3));
            log.info("Preloading " + addressRepository.save(address4));

        };
    }
}