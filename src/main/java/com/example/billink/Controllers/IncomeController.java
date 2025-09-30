package com.example.billink.Controllers;

import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Services.IncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IncomeController {
    Logger logger = LoggerFactory.getLogger(IncomeController.class);
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @QueryMapping
    public IncomeDTO getIncome(@Argument int incomeId) throws NoSuchElementException {
        logger.info("IncomeController-getIncome: Start");
        return incomeService.getIncome((long) incomeId);
    }

    @MutationMapping
    public IncomeDTO createIncome(@Argument IncomeDTO incomeDTO){
        return incomeService.createIncome(incomeDTO);
    }

    @MutationMapping
    public IncomeDTO deleteIncome(@Argument int incomeId){
        return incomeService.deleteIncome((long) incomeId);
    }

    @MutationMapping
    public IncomeDTO updateIncome(@Argument int incomeId, @Argument IncomeDTO incomeDTO){
        return incomeService.updateIncome((long) incomeId, incomeDTO);
    }
}
