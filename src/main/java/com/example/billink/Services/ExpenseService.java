package com.example.billink.Services;

import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Mapper.ExpenseMapper;
import com.example.billink.Models.Expense;
import com.example.billink.Repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ExpenseService {

    public ExpenseDTO getExpense(Long expenseId);

    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO expenseDTO);

    public ExpenseDTO deleteExpense(Long expenseId);

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO);
}
