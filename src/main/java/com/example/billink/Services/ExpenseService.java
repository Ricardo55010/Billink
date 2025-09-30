package com.example.billink.Services;

import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.Mapper.ExpenseMapper;
import com.example.billink.Models.Expense;
import com.example.billink.Repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private final RabbitTemplate rabbitTemplate;

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository, RabbitTemplate rabbitTemplate){
        this.expenseRepository = expenseRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ExpenseDTO getExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId).get();
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO expenseDTO){
        Expense expense = expenseRepository.findById(expenseId).get();
        expense.setAmount(expenseDTO.getAmount());
        expense.setName(expenseDTO.getName());
        expenseRepository.save(expense);
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    public ExpenseDTO deleteExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId).get();
        expenseRepository.deleteById(expenseId);
        return ExpenseMapper.mapExpenseToExpenseDTO(expense);
    }

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO){
        Expense expense = ExpenseMapper.mapExpenseDTOToExpense(expenseDTO);
        expenseRepository.save(expense);
        return expenseDTO;
    }
}
