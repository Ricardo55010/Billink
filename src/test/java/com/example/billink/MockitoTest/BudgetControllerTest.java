package com.example.billink.MockitoTest;

import com.example.billink.Controllers.BudgetController;
import com.example.billink.DTO.BudgetDTO;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BudgetControllerTest {
    @Mock
    BudgetService budgetService;
    @InjectMocks
    BudgetController budgetController;
    @Test
    void testBudgetController(){

        BudgetDTO budget1 = new BudgetDTO("si",1L);
        given(budgetService.createBudget("si")).willReturn(budget1);
        BudgetDTO budget = budgetController.createBudget("si");
        Assertions.assertNotNull(budget);
    }
}
