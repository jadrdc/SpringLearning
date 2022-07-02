package com.agustin.demo.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "orders")
public class Order {

    private @Id
    @GeneratedValue Long id;

    @ManyToOne
    private Checkout checkout;


}
