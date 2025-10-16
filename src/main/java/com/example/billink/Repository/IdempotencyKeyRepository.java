package com.example.billink.Repository;

import com.example.billink.Models.Budget;
import com.example.billink.Models.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey,String> {


}
