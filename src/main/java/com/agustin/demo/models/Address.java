package com.agustin.demo.models;

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
@Table(name = "Addresses")
public class Address {
    private @Id
    @GeneratedValue Long id;
    private String description;

    public Address(String description)
    {
        this.description=description;
    }
}
