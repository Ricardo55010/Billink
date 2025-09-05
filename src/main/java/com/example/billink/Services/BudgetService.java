package com.example.billink.Services;

import com.example.billink.Configurations.RabbitMQConfig;
import com.example.billink.Controllers.BudgetController;
import com.example.billink.Models.Budget;
import com.example.billink.Repository.BudgetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    Logger logger = LoggerFactory.getLogger(BudgetService.class);
    private final RabbitTemplate rabbitTemplate;

    BudgetRepository budgetRepository;
    public BudgetService(BudgetRepository budgetRepository,RabbitTemplate rabbitTemplate){
        this.budgetRepository = budgetRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Budget> getBudget( int count,  int offset ){
        logger.info("BudgetService.getBudget: Start ");
        rabbitTemplate.convertAndSend("myExchange","my.routing.key", new String("Starting communication with another microservice"));
        return budgetRepository.getBudget(count, offset);
    }

    public Budget createBudget(String title){
        Budget budget = new Budget(title, "2");
        budgetRepository.createBudget(budget);
        return budget;
    }
}
