package com.example.billink.UnitTest;

import com.example.billink.Controllers.BudgetController;
import com.example.billink.Controllers.IncomeController;
import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Models.Budget;
import com.example.billink.Models.Income;
import com.example.billink.Services.BudgetService;
import com.example.billink.Services.IncomeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@GraphQlTest(IncomeController.class)
//@ExtendWith(MockitoExtension.class) not recommended when using both spring and mockito together
@Import(IncomeTestConfig.class) //alternative to @mockbean since it's been deprecated
public class IncomeControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private IncomeService incomeService;
    @Test
    void testGetIncome(){

        IncomeDTO income = new IncomeDTO(1L,1L,"Test",1L,1L);

        when(incomeService.getIncome(any(long.class))).thenReturn(income);

        graphQlTester.document("{ getIncome(incomeId:1) { name } }")
                .execute().path("getIncome.name").entity(
                        String.class
                ).isEqualTo(income.getName());
    }

    @Test
    void testDeleteIncome(){

        IncomeDTO income = new IncomeDTO(1L,1L,"Test",1L,1L);

        when(incomeService.deleteIncome(any(long.class))).thenReturn(income);

        graphQlTester.document("mutation { deleteIncome(incomeId:1) { name } }")
                .execute().path("deleteIncome.name").entity(
                        String.class
                ).isEqualTo(income.getName());
    }

    @Test
    void testUpdateIncome(){

        IncomeDTO income = new IncomeDTO(1L,1L,"Test",1L,1L);

        when(incomeService.updateIncome(any(Long.class),any(IncomeDTO.class))).thenReturn(income);

        graphQlTester.document("mutation { updateIncome(incomeId:1,income:{ id: 1, amount: 1, budgetId: 1, userId: 1,name:\"Test\"}) { name } }")
                .execute().path("updateIncome.name").entity(
                        String.class
                ).isEqualTo(income.getName());
    }

    @Test
    void testCreateIncome(){

        IncomeDTO income = new IncomeDTO(1L,1L,"Test",1L,1L);

        when(incomeService.createIncome(any(IncomeDTO.class))).thenReturn(income);

        graphQlTester.document("mutation { createIncome(income:{ id: 1, amount: 1, budgetId: 1, userId: 1,name:\"Test\"}) { name } }")
                .execute().path("createIncome.name").entity(
                        String.class
                ).isEqualTo(income.getName());
    }


}

//This generates a mock of UserService in the context of spring
//It could be in a separate file too
@TestConfiguration
class IncomeTestConfig {
    @Bean
    @Primary
    IncomeService incomeService() {
        return Mockito.mock(IncomeService.class);
    }
}