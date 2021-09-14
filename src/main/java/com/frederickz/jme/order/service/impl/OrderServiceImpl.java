package com.frederickz.jme.order.service.impl;

import com.frederickz.jme.infrastructure.RabbitMQConfig;
import com.frederickz.jme.order.domain.Order;
import com.frederickz.jme.order.repository.OrderRepository;
import com.frederickz.jme.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RabbitListener(queues = "JME")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Set<Order> findAll() {
        return new HashSet<>(orderRepository.findAll());
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void produceOrderMessage(Order order) {
        System.out.println("start produce message");
        rabbitTemplate.convertAndSend(RabbitMQConfig.JME_ORDER_QUEUE, order.toString());
        System.out.println("message produced");
    }

    @Override
    @RabbitHandler
    public void consumeOrderMessage(String message) {
        System.out.println("Received: " + message);
    }
}
