package com.investment_management.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "account_code")
    private Number accountCode;

    @Column(name = "name")
    private String name;

    @Column(name = "ifsc")
    private String ifsc;

    @Column(name = "userId")
    private String userId;

    @Column(name = "balance")
    private Long amount;

    public Bank(Number accountCode, String name, String ifsc) {
        this.accountCode = accountCode;
        this.name = name;
        this.ifsc = ifsc;
    }
}
