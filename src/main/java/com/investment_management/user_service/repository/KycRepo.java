package com.investment_management.user_service.repository;

import com.investment_management.user_service.entity.Kyc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KycRepo extends JpaRepository<Kyc, UUID> {
    Optional<Kyc> findByUserId(String userId);
}
