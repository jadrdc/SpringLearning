package com.agustin.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutAddAddressRequest {

    private long addressId;
    private long checkoutId;

}
