package com.example.billink.Controllers;

import com.example.billink.DTO.BudgetDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestHeader;

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
    public BudgetDTO getBudget(@Argument int id ) throws NoSuchElementException{
        return budgetService.getBudget((long)id);
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
    public BudgetDTO createBudget(@Argument String title, @ContextValue("idempotency-key") String idempotencyKey){
        BudgetDTO budget = budgetService.createBudget(idempotencyKey, title);
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
