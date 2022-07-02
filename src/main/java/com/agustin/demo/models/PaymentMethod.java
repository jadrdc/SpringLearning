package com.agustin.demo.models;

import com.agustin.demo.repositories.PaymentRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "PaymentMethods")
public class PaymentMethod {
    private @Id
    @GeneratedValue Long id;
    private String description;

    public PaymentMethod(String description)
    {
        this.description=description;
    }
}
