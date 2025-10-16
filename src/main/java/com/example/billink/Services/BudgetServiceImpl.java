package com.example.billink.Services;

import com.example.billink.DTO.BudgetDTO;
import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Exceptions.NoValidIdempotencyKey;
import com.example.billink.Mapper.BudgetMapper;
import com.example.billink.Models.Budget;
import com.example.billink.Models.IdempotencyKey;
import com.example.billink.Repository.BudgetRepository;
import com.example.billink.Repository.IdempotencyKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetServiceImpl  implements BudgetService{
    Logger logger = LoggerFactory.getLogger(BudgetServiceImpl.class);
    private IdempotencyKeyRepository idempotencyKeyRepository;
    private final RabbitTemplate rabbitTemplate;

    BudgetRepository budgetRepository;
    public BudgetServiceImpl(BudgetRepository budgetRepository, RabbitTemplate rabbitTemplate, IdempotencyKeyRepository idempotencyKeyRepository){
        this.budgetRepository = budgetRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.idempotencyKeyRepository = idempotencyKeyRepository;
    }

    @Transactional
    public BudgetDTO getBudget(Long id ){
        Budget budget = budgetRepository.findById(id).orElseThrow(()->new NoSuchElementException("No budget found"));
        return BudgetMapper.mapBudgetToBudgetDTO(budget);
    }

    @Transactional
    public BudgetDTO createBudget(String idempotencyKey, String title) {
        IdempotencyKey savedKey = idempotencyKeyRepository.findById(idempotencyKey).orElse(null);
        if (savedKey != null) {
            if (savedKey.getExpiryDate().isBefore(LocalDateTime.now())) {
                idempotencyKeyRepository.delete(savedKey);
            } else {
                throw new NoValidIdempotencyKey("This is a duplicated valid idempotency key with valid time");
            }
        }

        Budget budget = new Budget(title);
        budgetRepository.saveAndFlush(budget);

        IdempotencyKey newKey = new IdempotencyKey();

        newKey.setKey(idempotencyKey);
        newKey.setResponse("Created budget with ID" + budget.getId());
        newKey.setExpiryDate(LocalDateTime.now().plusHours(24)); // 24-hour expiration added here, in the server
        idempotencyKeyRepository.save(newKey);
        return BudgetMapper.mapBudgetToBudgetDTO(budget);
    }
}
