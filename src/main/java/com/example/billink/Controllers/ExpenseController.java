package com.example.billink.Controllers;

import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Expense;
import com.example.billink.Services.BudgetService;
import com.example.billink.Services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    Logger logger = LoggerFactory.getLogger(ExpenseController.class);
    ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @QueryMapping
    public ExpenseDTO getExpense(@Argument int expenseId ) throws NoSuchElementException{
        logger.info("BudgetController-getBudget: Start");
        return expenseService.getExpense((long)expenseId);
    }

    @MutationMapping
    public ExpenseDTO createExpense(@Argument ExpenseDTO expenseDTO){

        return expenseService.createExpense(expenseDTO);
    }
    @MutationMapping
    public ExpenseDTO deleteExpense(@Argument int expenseId){

        return expenseService.deleteExpense((long)expenseId);
    }
    @MutationMapping
    public ExpenseDTO updateExpense(@Argument int expenseId,@Argument ExpenseDTO expenseDTO){

        return expenseService.updateExpense((long)expenseId,expenseDTO);
    }

}
