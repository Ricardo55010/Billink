package com.example.billink.Mapper;

import com.example.billink.DTO.BudgetDTO;
import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Expense;

public class BudgetMapper {
    public static BudgetDTO mapBudgetToBudgetDTO(Budget budget){
        return new BudgetDTO(budget.getTitle(), budget.getUserId());
    }

    public static Budget mapBudgetDTOToBudget(BudgetDTO budgetDTO){
        return new Budget(budgetDTO.getTitle());
    }
}
