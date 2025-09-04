package com.example.billink.Services;

import com.example.billink.Configurations.RabbitMQConfig;
import com.example.billink.Models.Budget;
import com.example.billink.Repository.BudgetRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    private final RabbitTemplate rabbitTemplate;

    BudgetRepository budgetRepository;
    public BudgetService(BudgetRepository budgetRepository,RabbitTemplate rabbitTemplate){
        this.budgetRepository = budgetRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Budget> getBudget( int count,  int offset ){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, "Starting communication with another microservice");
        return budgetRepository.getBudget(count, offset);
    }

    public Budget createBudget(String title){
        Budget budget = new Budget(title, "2");
        budgetRepository.createBudget(budget);
        return budget;
    }
}
