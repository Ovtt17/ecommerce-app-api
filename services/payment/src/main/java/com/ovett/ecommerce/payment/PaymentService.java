package com.ovett.ecommerce.payment;

import com.ovett.ecommerce.notification.NotificationProducer;
import com.ovett.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));
        notificationProducer.sendPaymentNotification(
                PaymentNotificationRequest.builder()
                        .orderReference(request.orderReference())
                        .amount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .customerFistName(request.customer().firstName())
                        .customerLastName(request.customer().lastName())
                        .customerEmail(request.customer().email())
                        .build()
        );
        return payment.getId();
    }
}
