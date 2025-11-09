package com.flyasiana.orderservice.service;

import com.flyasiana.orderservice.dto.OrderDto;
import com.flyasiana.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
