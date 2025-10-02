package com.example.billink.Services;

import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Mapper.ExpenseMapper;
import com.example.billink.Mapper.IncomeMapper;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Income;
import com.example.billink.Repository.BudgetRepository;
import com.example.billink.Repository.IncomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IncomeService {


    public IncomeDTO getIncome(Long incomeId);

    public IncomeDTO updateIncome(Long incomeId, IncomeDTO incomeDTO);

    public IncomeDTO deleteIncome(Long incomeId);

    public IncomeDTO createIncome(IncomeDTO incomeDTO);
}
