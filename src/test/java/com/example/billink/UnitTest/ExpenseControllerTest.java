package com.example.billink.UnitTest;

import com.example.billink.Controllers.ExpenseController;
import com.example.billink.DTO.ExpenseDTO;
import com.example.billink.Services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@GraphQlTest(ExpenseController.class)
//@ExtendWith(MockitoExtension.class) not recommended when using both spring and mockito together
@Import(ExpenseTestConfig.class) //alternative to @mockbean since it's been deprecated
public class ExpenseControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private ExpenseService expenseService;

    @Test
    void testGetExpense(){

        ExpenseDTO expense = new ExpenseDTO(1L,1L,"Test",1L,1L);

        when(expenseService.getExpense(any(long.class))).thenReturn(expense);

        graphQlTester.document("{ getExpense(expenseId:1) { name } }")
                .execute().path("getExpense.name").entity(
                        String.class
                ).isEqualTo(expense.getName());
    }

    @Test
    void testDeleteExpense(){

        ExpenseDTO expense = new ExpenseDTO(1L,1L,"Test",1L,1L);

        when(expenseService.deleteExpense(any(long.class))).thenReturn(expense);

        graphQlTester.document("mutation { deleteExpense(expenseId:1) { name } }")
                .execute().path("deleteExpense.name").entity(
                        String.class
                ).isEqualTo(expense.getName());
    }

    @Test
    void testUpdateExpense(){

        ExpenseDTO expense = new ExpenseDTO(1L,1L,"Test",1L,1L);

        when(expenseService.updateExpense(any(Long.class),any(ExpenseDTO.class))).thenReturn(expense);

        graphQlTester.document("mutation { updateExpense(expenseId:1,expense:{ id: 1, amount: 1, budgetId: 1, userId: 1,name:\"Test\"}) { name } }")
                .execute().path("updateExpense.name").entity(
                        String.class
                ).isEqualTo(expense.getName());
    }

    @Test
    void testCreateExpense(){

        ExpenseDTO expense = new ExpenseDTO(1L,1L,"Test",1L,1L);

        when(expenseService.createExpense(any(ExpenseDTO.class))).thenReturn(expense);

        graphQlTester.document("mutation { createExpense(expense:{ id: 1, amount: 1, budgetId: 1, userId: 1,name:\"Test\"}) { name } }")
                .execute().path("createExpense.name").entity(
                        String.class
                ).isEqualTo(expense.getName());
    }
}

//This generates a mock of ExpenseService in the context of spring
//It could be in a separate file too
@TestConfiguration
class ExpenseTestConfig {
    @Bean
    @Primary
    ExpenseService expenseService() {
        return Mockito.mock(ExpenseService.class);
    }
}
