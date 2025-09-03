package com.example.billink.Services;

import com.example.billink.Models.Budget;
import com.example.billink.Repository.BudgetRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    BudgetRepository budgetRepository;
    public BudgetService(BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
    }

    public List<Budget> getBudget( int count,  int offset ){

        return budgetRepository.getBudget(count, offset);
    }

    public Budget createBudget(String title){
        Budget budget = new Budget(title, "2");
        budgetRepository.createBudget(budget);
        return budget;
    }
}
