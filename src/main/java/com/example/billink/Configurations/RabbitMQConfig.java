package com.example.billink.Configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "TestQueue";

    @Bean
    public Queue testQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
