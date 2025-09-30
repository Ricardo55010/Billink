package com.example.billink.Mapper;

import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Models.Income;

public class IncomeMapper {
    public static IncomeDTO mapIncomeToIncomeDTO(Income income){
        return new IncomeDTO(income.getBudgetId(), income.getUserId(), income.getName(), income.getAmount(), income.getId());
    }

    public static Income mapIncomeDTOToIncome(IncomeDTO incomeDTO){
        return new Income(incomeDTO.getBudgetId(), incomeDTO.getUserId(), incomeDTO.getName(), incomeDTO.getAmount(), incomeDTO.getId());
    }
}
