package com.frederickz.jme.order;

import com.frederickz.jme.infrastructure.CrudService;
import com.frederickz.jme.order.Order;

import java.util.UUID;

public interface OrderService extends CrudService<Order, UUID> {

    void produceOrderMessage(Order order);

    void consumeOrderMessage(String message);

}
