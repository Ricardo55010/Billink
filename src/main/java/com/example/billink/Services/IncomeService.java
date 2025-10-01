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

import java.util.List;

@Service
public class IncomeService {
    Logger logger = LoggerFactory.getLogger(IncomeService.class);
    private final RabbitTemplate rabbitTemplate;

    IncomeRepository incomeRepository;
    public IncomeService(IncomeRepository incomeRepository, RabbitTemplate rabbitTemplate){
        this.incomeRepository = incomeRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public IncomeDTO getIncome(Long incomeId){

         Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be retrieved"));
         return IncomeMapper.mapIncomeToIncomeDTO(income);
    }

    public IncomeDTO updateIncome(Long incomeId, IncomeDTO incomeDTO){
        Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be updated"));
        income.setAmount(incomeDTO.getAmount());
        income.setName(incomeDTO.getName());
        incomeRepository.save(income);
        return IncomeMapper.mapIncomeToIncomeDTO(income);
    }

    public IncomeDTO deleteIncome(Long incomeId){
        Income income = incomeRepository.findById(incomeId).orElseThrow(()-> new NoSuchElementException("No income to be deleted"));
        incomeRepository.deleteById(incomeId);
        return IncomeMapper.mapIncomeToIncomeDTO(income);

    }

    public IncomeDTO createIncome(IncomeDTO incomeDTO){
        Income income = IncomeMapper.mapIncomeDTOToIncome(incomeDTO);
        incomeRepository.save(income);
        return incomeDTO;
    }
}
