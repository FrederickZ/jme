package com.frederickz.jme.service;

import com.frederickz.jme.model.Order;

import java.util.UUID;

public interface OrderService extends CrudService<Order, UUID> {

    void produceOrderMessage(Order order);

}
