package com.example.billink.Services;

import com.example.billink.DTO.BudgetDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Mapper.BudgetMapper;
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
    public BudgetDTO getBudget(Long id ){
        Budget budget = budgetRepository.findById(id).orElseThrow(()->new NoSuchElementException("No budget found"));
        return BudgetMapper.mapBudgetToBudgetDTO(budget);
    }

    @Transactional
    public BudgetDTO createBudget(String title){
        Budget budget = new Budget(title);
        logger.info("esto" + title);
        budgetRepository.saveAndFlush(budget);
        return BudgetMapper.mapBudgetToBudgetDTO(budget);
    }
}
