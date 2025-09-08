package com.example.billink.Configurations;

import com.example.billink.DTO.UserDTO;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "TestQueue";

    @Bean
    public Queue testQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange myExchange() {
        return new TopicExchange("myExchange");
    }

    @Bean
    public Binding binding(Queue myQueue, TopicExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with("my.routing.key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(UserDTO userDTO) {
        System.out.println("Received message: " + userDTO);
    }

}
