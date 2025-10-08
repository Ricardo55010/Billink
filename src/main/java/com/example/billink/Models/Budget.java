package com.example.billink.Models;import jakarta.persistence.*;
import org.springframework.graphql.data.federation.EntityMapping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="user_id")
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "budget_income",
            joinColumns = @JoinColumn(name="budget_id"),
            inverseJoinColumns = @JoinColumn(name="income_id") )
    private List<Income> incomeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Budget_Expense",
            joinColumns = @JoinColumn(name="budget_id"),
            inverseJoinColumns = @JoinColumn(name="expense_id") )
    private List<Expense> expenseList = new ArrayList<>();


    public Budget(String title) {
        this.title = title;
    }
    public Budget() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
