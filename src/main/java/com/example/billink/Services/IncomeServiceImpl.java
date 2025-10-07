package com.example.billink.Services;

import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Mapper.ExpenseMapper;
import com.example.billink.Mapper.IncomeMapper;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Expense;
import com.example.billink.Models.Income;
import com.example.billink.Repository.BudgetRepository;
import com.example.billink.Repository.IncomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class IncomeServiceImpl implements IncomeService{
    Logger logger = LoggerFactory.getLogger(IncomeServiceImpl.class);
    private final RabbitTemplate rabbitTemplate;

    IncomeRepository incomeRepository;
    BudgetRepository budgetRepository;
    public IncomeServiceImpl(IncomeRepository incomeRepository, BudgetRepository budgetRepository, RabbitTemplate rabbitTemplate){
        this.incomeRepository = incomeRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public IncomeDTO getIncome(Long incomeId){

         Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be retrieved"));
         return IncomeMapper.mapIncomeToIncomeDTO(income);
    }

    @Transactional
    public IncomeDTO updateIncome(Long incomeId, IncomeDTO incomeDTO){
        Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be updated"));
        income.setAmount(incomeDTO.getAmount());
        income.setName(incomeDTO.getName());
        incomeRepository.save(income);
        return IncomeMapper.mapIncomeToIncomeDTO(income);
    }

    @Transactional
    public IncomeDTO deleteIncome(Long incomeId){
        Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be deleted"));
        incomeRepository.deleteById(incomeId);
        return IncomeMapper.mapIncomeToIncomeDTO(income);

    }

    @Transactional
    public IncomeDTO createIncome(IncomeDTO incomeDTO){
        Income income = IncomeMapper.mapIncomeDTOToIncome(incomeDTO);
        Budget budget = budgetRepository.findById(incomeDTO.getBudgetId()).orElseThrow(()->new NoSuchElementException("No budget existent"));
        if(budget.getIncomeList()!=null){
            budget.getIncomeList().add(income);
        }
        else{
            budget.setIncomeList(new ArrayList<>());
            budget.getIncomeList().add(income);
        }
        budgetRepository.save(budget);
        return incomeDTO;
    }
}
