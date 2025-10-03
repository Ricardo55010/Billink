package com.example.billink.Services;

import com.example.billink.Models.Budget;
import com.example.billink.Repository.BudgetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BudgetServiceImpl  implements BudgetService{
    Logger logger = LoggerFactory.getLogger(BudgetServiceImpl.class);
    private final RabbitTemplate rabbitTemplate;

    BudgetRepository budgetRepository;
    public BudgetServiceImpl(BudgetRepository budgetRepository, RabbitTemplate rabbitTemplate){
        this.budgetRepository = budgetRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public List<Budget> getBudget( int count,  int offset ){
        logger.info("BudgetService.getBudget: Start ");
        return budgetRepository.findAll();
    }

    @Transactional
    public Budget createBudget(String title){
        Budget budget = new Budget(title, 1L);
        budgetRepository.save(budget);
        return budget;
    }
}
