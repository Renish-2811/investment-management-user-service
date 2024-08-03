package com.investment_management.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Kyc")
@Data
public class Kyc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column
    private String userId;

    @Column
    private String aadharCard;

    @Column
    private String panCard;

    @Column
    private String status;

    public Kyc( String aadharCard, String panCard) {
        this.aadharCard = aadharCard;
        this.panCard = panCard;
    }
}
