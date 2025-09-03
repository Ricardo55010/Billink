package com.example.billink.Models;

public class User{
    String name;
    Integer age;
    Integer wage;
    String employment;

    public User(String name, Integer age, Integer wage) {
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWage() {
        return wage;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }
}