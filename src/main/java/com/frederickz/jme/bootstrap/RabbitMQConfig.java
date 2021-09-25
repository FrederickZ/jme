package com.frederickz.jme.bootstrap;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String JME_QUEUE_NAME = "JME";

    @Bean
    public Queue queue() {
        return new Queue(JME_QUEUE_NAME, false);
    }

}
