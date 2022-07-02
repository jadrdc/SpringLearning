package com.agustin.demo.repositories;

import com.agustin.demo.models.Address;
import com.agustin.demo.models.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
