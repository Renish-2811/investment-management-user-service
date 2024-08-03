package com.investment_management.user_service.repository;

import com.investment_management.user_service.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BankRepo extends JpaRepository<Bank, UUID> {

    Optional<Bank> findByUserId(String userId);


}
