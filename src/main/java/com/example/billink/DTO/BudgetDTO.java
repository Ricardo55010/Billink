package com.example.billink.DTO;import jakarta.persistence.*;


public class BudgetDTO {

    private Long id;
    private String title;
    private Long userId;

    public BudgetDTO(String title, Long userId) {
        this.title = title;
        this.userId = userId;
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
