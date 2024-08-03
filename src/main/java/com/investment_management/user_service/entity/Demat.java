package com.investment_management.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "demat_balances")
@Data
@NoArgsConstructor
public class Demat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column (name = "user_id")
    private String userId;

    @Column(name = "type" , unique = true)
    private String type;

    @Column(name = "amount")
    private Long amount;

    public Demat(String type, Long amount) {
        this.type = type;
        this.amount = amount;
    }

}
