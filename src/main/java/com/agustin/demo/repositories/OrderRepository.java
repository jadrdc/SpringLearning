package com.agustin.demo.repositories;

import com.agustin.demo.models.Checkout;
import com.agustin.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
