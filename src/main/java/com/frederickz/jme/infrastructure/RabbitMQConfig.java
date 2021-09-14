package com.frederickz.jme.infrastructure;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String JME_ORDER_QUEUE = "JME";

    @Bean
    Queue queue() {
        return new Queue(JME_ORDER_QUEUE, false);
    }

}
