package com.example.billink.Repository;

import com.example.billink.Models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {


}
