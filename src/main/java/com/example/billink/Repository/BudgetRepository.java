package com.example.billink.Repository;

import com.example.billink.Models.Budget;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BudgetRepository {
    static List<Budget> list = new ArrayList<>();
    static {
        list.add(new Budget("Ejemplo 1","1"));
        list.add(new Budget("Ejemplo 2","2"));
        list.add(new Budget("Ejemplo 3","3"));
    }

    public List<Budget> getBudget( int count, int offset ){

        return list;
    }

    public void createBudget (Budget budget){
        list.add(budget);
    }

}
