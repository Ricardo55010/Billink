package com.example.billink.Controllers;

import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BudgetController {
    Logger logger = LoggerFactory.getLogger(BudgetController.class);
    BudgetService budgetService;
    public BudgetController(BudgetService budgetService){
        this.budgetService = budgetService;
    }
    @QueryMapping
    public List<Budget> getBudget(@Argument int count, @Argument int offset ) throws NoSuchElementException{
        logger.info("BudgetController-getBudget: Start");
        if(count ==1)
            throw new NoSuchElementException("No hay");
        return budgetService.getBudget(count,offset);
    }
/*
* {
  getBudget (count: 2,offset:  1)
{
  id
  title
}
}*/
    @MutationMapping
    public Budget createBudget(@Argument String title){
        Budget budget = budgetService.createBudget(title);
        return budget;
    }
    /*
    * mutation{
  createBudget(title:"jajaj")
{
  title
}
}*/
}
