package com.frederickz.jme.service.impl;

import com.frederickz.jme.bootstrap.RabbitMQConfig;
import com.frederickz.jme.component.MatchEngine;
import com.frederickz.jme.model.Order;
import com.frederickz.jme.repository.OrderRepository;
import com.frederickz.jme.service.OrderService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RabbitListener(queues = RabbitMQConfig.JME_QUEUE_NAME)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final MatchEngine matchEngine;

    public OrderServiceImpl(OrderRepository orderRepository,
                            RabbitTemplate rabbitTemplate, Queue queue, MatchEngine matchEngine) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.matchEngine = matchEngine;
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
        Order savedOrder = orderRepository.save(order);
        produceMessage(savedOrder);
        return savedOrder;
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
    public void produceMessage(Order order) {
        rabbitTemplate.convertAndSend(queue.getName(), order.toMessage());
    }

    @Override
    @RabbitHandler
    public void consumeMessage(String message) {
        Order order = findById(UUID.fromString(message.split("\\|")[0]));
        matchEngine.distribute(order);
    }

}
