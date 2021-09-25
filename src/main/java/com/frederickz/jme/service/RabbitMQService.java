package com.frederickz.jme.service;

import com.frederickz.jme.model.Order;

public interface RabbitMQService<T> {

    void produceMessage(T object);
    void consumeMessage(String message);

}
