package com.agustin.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutAddPaymentRequest {

    private long paymentId;
    private long checkoutId;

}
