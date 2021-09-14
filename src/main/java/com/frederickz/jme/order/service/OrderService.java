package com.frederickz.jme.order.service;

import com.frederickz.jme.infrastructure.CrudService;
import com.frederickz.jme.order.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.UUID;

public interface OrderService extends CrudService<Order, UUID> {

    void produceOrderMessage(Order order);

    void consumeOrderMessage(String message);

}
