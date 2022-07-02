package com.agustin.demo.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String lastName;

    private String dni;

    public User(String name,String lastName,String dni) {
        this.name = name;
        this.lastName=lastName;
        this.dni=dni;
    }
}
