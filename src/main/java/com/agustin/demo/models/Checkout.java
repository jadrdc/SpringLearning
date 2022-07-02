package com.agustin.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Checkout {
    private @Id
    @GeneratedValue Long id;

    /*@OneToMany(mappedBy="checkoutProduct",fetch = FetchType.LAZY)*/
    @OneToMany
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private List<Product> productList;

    @ManyToOne
    private User user;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private Address address;

}
