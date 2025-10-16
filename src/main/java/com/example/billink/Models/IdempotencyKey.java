package com.example.billink.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class IdempotencyKey {
    @Id
    @Column(name = "id")
    private String key;
    private String response;
    private LocalDateTime expiryDate;

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
