package com.investment_management.user_service.repository;

import com.investment_management.user_service.entity.Demat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceRepo extends JpaRepository<Demat, UUID> {

    Optional<Demat> findByUserIdAndType(String userId,String type);

    List<Demat> findByUserId(String userId);
}
