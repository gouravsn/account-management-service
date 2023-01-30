package com.anz.demo.accountmanagementservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto {

    private long transactionId;
    private LocalDate transactionDate;
    private double transactionAmount;
    private String transactionType;
    private String transactionNarrative;
}
