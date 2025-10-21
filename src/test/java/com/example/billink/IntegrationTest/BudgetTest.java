package com.example.billink.IntegrationTest;

import com.example.billink.BillinkApplication;
import com.example.billink.Models.Budget;
import com.example.billink.Services.BudgetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = BillinkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //point where the main class is to find beans tbat can be injected during the test
//checar para que es randomport en springbootest environment
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
//THIS ONLY COMES WITH @GRAPHQL ANNOTATION, WHEN SPRINGBOOTTEST IS USED ITS GOT TO BE MANUALLY ADDED
public class BudgetTest {

    @Autowired
    MockMvc mockMvc;
    private HttpGraphQlTester graphQlTester;
    @Autowired
    private BudgetService budgetService;

    @BeforeEach
    void setup() {
        var client = MockMvcWebTestClient.bindTo(mockMvc)
                .baseUrl("/graphql")
                .build();

        this.graphQlTester = HttpGraphQlTester.create(client);//this is needed in order to mutate the graphqltester and add http headers
        //it also needs a spring webflux dependency to work
    }
    @Test
    void testGetBudgets() {


        graphQlTester.mutate().header("idempotency-key","llave").build().document("mutation { createBudget(title: \"Richard\") { title } }")
                .execute()
                .path("createBudget.title").entity(String.class).isEqualTo("Richard");
        graphQlTester.document("{ getBudget(id: 1) { title } }")
                .execute().path("getBudget.title").entity(
                        String.class
                ).isEqualTo("Richard");
        //documentName looks for a graphql file whereas document let us use a query directly
        //path let us specify the field we want to compare starting from the query name
        //entity or entitylist depend on the amount of results, but helps us to do the mapping
        //isEqualTo let us do some testing*/


    }
}
