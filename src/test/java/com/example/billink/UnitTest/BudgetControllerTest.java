package com.example.billink.UnitTest;

import com.example.billink.Controllers.BudgetController;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
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


@GraphQlTest(BudgetController.class)
//@ExtendWith(MockitoExtension.class) not recommended when using both spring and mockito together
@Import(BudgetTestConfig.class) //alternative to @mockbean since it's been deprecated
public class BudgetControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private BudgetService budgetService;
    @Test
    void testGetUsers(){

        List<Budget> budget = new ArrayList<>();
        budget.add(new Budget("Example",1L));
        budget.add(new Budget("Example",2L));
        when(budgetService.getBudget(any(int.class),any(int.class))).thenReturn(budget);

        graphQlTester.document("{ getBudget(count: 5, offset:10) { title } }")
                .execute().path("getBudget").entityList(
                        Budget.class
                ).hasSize(budget.size());
    }


}

//This generates a mock of UserService in the context of spring
//It could be in a separate file too
@TestConfiguration
class BudgetTestConfig {
    @Bean
    @Primary
    BudgetService budgetService() {
        return Mockito.mock(BudgetService.class);
    }
}