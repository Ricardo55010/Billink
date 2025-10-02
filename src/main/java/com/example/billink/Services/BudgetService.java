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
public interface BudgetService {


    public List<Budget> getBudget( int count,  int offset );
    public Budget createBudget(String title);
}
