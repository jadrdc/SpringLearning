package com.agustin.demo.models;

import lombok.*;

import java.util.Objects;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public
class Product {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private Double price;

    private int quantity = 0;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id")
    private Checkout checkoutProduct;*/

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}