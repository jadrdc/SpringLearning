package com.agustin.demo.response;

import com.agustin.demo.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {

    private long Id;
    private  String userName;
    private  double total;
    private List<Product> products;
    private  String address;
    private  String payment;


}
