package com.agustin.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutAddProductRequest {

    private long userId;
    private long productId;
    private long quantity;
    private long checkoutId;

}
