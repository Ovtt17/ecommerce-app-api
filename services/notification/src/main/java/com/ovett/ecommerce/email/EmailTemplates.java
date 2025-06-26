package com.ovett.ecommerce.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("templates/payment-confirmation.html", "Payment Successfully processed"),
    ORDER_CONFIRMATION("templates/order-confirmation.html", "Order Confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;
}
