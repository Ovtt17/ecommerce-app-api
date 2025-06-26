package com.ovett.ecommerce.order;

import com.ovett.ecommerce.customer.CustomerClient;
import com.ovett.ecommerce.exception.BusinessException;
import com.ovett.ecommerce.kafka.OrderConfirmation;
import com.ovett.ecommerce.kafka.OrderProducer;
import com.ovett.ecommerce.orderline.OrderLineRequest;
import com.ovett.ecommerce.orderline.OrderLineService;
import com.ovett.ecommerce.payment.PaymentClient;
import com.ovett.ecommerce.payment.PaymentRequest;
import com.ovett.ecommerce.product.ProductClient;
import com.ovett.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        var order = orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                null,
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .orderReference(order.getReference())
                        .totalAmount(order.getTotalAmount())
                        .paymentMethod(order.getPaymentMethod())
                        .customer(customer)
                        .products(purchasedProducts)
                        .build()
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order with ID: " + orderId));
    }
}
