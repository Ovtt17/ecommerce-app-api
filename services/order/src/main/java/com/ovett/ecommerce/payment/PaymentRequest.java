package com.ovett.ecommerce.payment;

import com.ovett.ecommerce.customer.CustomerResponse;
import com.ovett.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
