package com.anz.demo.accountmanagementservice.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "transaction_details", schema = "account_management")
@Entity
@Getter
@Setter
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_narrative")
    private String transactionNarrative;


}
