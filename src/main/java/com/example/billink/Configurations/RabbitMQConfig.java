package com.example.billink.Configurations;

import com.example.billink.DTO.UserDTO;
import com.example.billink.Models.Budget;
import com.example.billink.Repository.BudgetRepository;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "TestQueue";

    /* */
    static private BudgetRepository budgetRepository;
    @Autowired
    public void init(BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
    }
    /*Trick: this is injecting a bean of the repository,
    useful because we cant inject it directly (statelss beans)
    into the rabbitlistener, this is like a setter
    https://stackoverflow.com/questions/12155632/injecting-a-spring-dependency-into-a-jpa-entitylistener
    */

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
        budgetRepository.save(new Budget("Primero"));
    }

}
