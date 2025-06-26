package com.ovett.ecommerce.notification;

import com.ovett.ecommerce.payment.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFistName,
        String customerLastName,
        String customerEmail
) {
}
