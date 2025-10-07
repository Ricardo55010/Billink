package com.example.billink.Services;

import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Mapper.ExpenseMapper;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Expense;
import com.example.billink.Repository.BudgetRepository;
import com.example.billink.Repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final RabbitTemplate rabbitTemplate;

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, BudgetRepository budgetRepository, RabbitTemplate rabbitTemplate){
        this.expenseRepository = expenseRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public ExpenseDTO getExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()-> new NoSuchElementException("No expense existent to retrieve"));
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    @Transactional
    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO expenseDTO){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()-> new NoSuchElementException("No expense existent to be updated"));
        expense.setAmount(expenseDTO.getAmount());
        expense.setName(expenseDTO.getName());
        expenseRepository.save(expense);
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    @Transactional
    public ExpenseDTO deleteExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()-> new NoSuchElementException("No expense existent to be deleted"));
        expenseRepository.deleteById(expenseId);
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    @Transactional
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO){
        Expense expense = ExpenseMapper.mapExpenseDTOToExpense(expenseDTO);
        Budget budget = budgetRepository.findById(expenseDTO.getBudgetId()).orElseThrow(()->new NoSuchElementException("No budget existent"));
        if(budget.getExpenseList()!=null){
            budget.getExpenseList().add(expense);
        }
        else{
            budget.setExpenseList(new ArrayList<>());
            budget.getExpenseList().add(expense);
        }
        budgetRepository.save(budget);
        return expenseDTO;
    }
}
