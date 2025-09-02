package com.example.billink.Controllers;

import com.example.billink.Models.Budget;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BudgetController {
static List<Budget> list = new ArrayList<>();
    static {
        list.add(new Budget("Ejemplo 1","1"));
        list.add(new Budget("Ejemplo 2","2"));
        list.add(new Budget("Ejemplo 3","3"));
    }
    @QueryMapping
    public List<Budget> getBudget(@Argument int count, @Argument int offset ){

        return list;
    }
/*
* {
  getBudget (count: 2,offset:  1)
{
  id
  title
}
}*/
    @MutationMapping
    public Budget createBudget(@Argument String title){
        Budget budget = new Budget(title, "2");
        list.add(budget);
        return budget;
    }
    /*
    * mutation{
  createBudget(title:"jajaj")
{
  title
}
}*/
}
