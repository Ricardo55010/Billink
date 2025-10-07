package com.example.billink.Models;import jakarta.persistence.*;

@Entity
@Table(name="income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="amount")
    private Long amount;
    @Column(name="budget_id")
    private Long budgetId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="name")
    private String name;

    public Income(Long budgetId, Long userId, String name, Long amount, Long id) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.id = id;
    }

    public Income(Long budgetId, Long userId, String name, Long amount) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
    }

    public Income() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
