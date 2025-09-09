package com.example.billink.Models;import jakarta.persistence.*;
import org.springframework.graphql.data.federation.EntityMapping;

@Entity
@Table(name="Budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="userId")
    private Long userId;

    public Budget(String title,Long userId) {
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
