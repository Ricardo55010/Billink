package com.example.billink.Mapper;

import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Models.Expense;
import com.example.billink.Models.Income;

public class ExpenseMapper {
    public static ExpenseDTO mapExpenseToExpenseDTO(Expense expense){
        return new ExpenseDTO(expense.getBudgetId(), expense.getUserId(), expense.getName(), expense.getAmount(), expense.getId());
    }

    public static Expense mapExpenseDTOToExpense(ExpenseDTO expenseDTO){
        return new Expense(expenseDTO.getBudgetId(), expenseDTO.getUserId(), expenseDTO.getName(), expenseDTO.getAmount(), expenseDTO.getId());
    }
}
