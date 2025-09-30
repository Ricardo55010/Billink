package com.example.billink.Repository;

import com.example.billink.Models.Budget;
import com.example.billink.Models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {


}
