package com.ovett.ecommerce.kafka;

import com.ovett.ecommerce.customer.CustomerResponse;
import com.ovett.ecommerce.order.PaymentMethod;
import com.ovett.ecommerce.product.PurchaseResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
