package com.example.billink.IntegrationTest;

import com.example.billink.BillinkApplication;
import com.example.billink.DTO.IncomeDTO;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = BillinkApplication.class) //point where the main class is to find beans tbat can be injected during the test
//checar para que es randomport en springbootest environment
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureGraphQlTester
//THIS ONLY COMES WITH @GRAPHQL ANNOTATION, WHEN SPRINGBOOTTEST IS USED ITS GOT TO BE MANUALLY ADDED
public class IncomeTest {
    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private BudgetService budgetService;
    @Test
    void testGetIncome(){

   /*     graphQlTester.document("mutation { createUser(name: \"Richard\") { id name } }")
                .execute()
                .path("createUser.name").entity(String.class).isEqualTo("Richard");
        graphQlTester.document("{ getUser(id: 1) { name } }")
                .execute().path("getUser.name").entity(
                        String.class
                ).isEqualTo("Richard");
        //documentName looks for a graphql file whereas document let us use a query directly
        //path let us specify the field we want to compare starting from the query name
        //entity or entitylist depend on the amount of results, but helps us to do the mapping
        //isEqualTo let us do some testing*/
        graphQlTester.document("mutation { createIncome(income: {id:1 ,name:\"Richard\", userId: 1, budgetId:1, amount:1}) { id name } }")
                .execute()
                .path("createIncome.name").entity(String.class).isEqualTo("Richard");
        graphQlTester.document("{ getIncome(incomeId:1) { name } }")
                .execute().path("getIncome.name").entity(
                        String.class
                ).isEqualTo("Richard");
    }
}
