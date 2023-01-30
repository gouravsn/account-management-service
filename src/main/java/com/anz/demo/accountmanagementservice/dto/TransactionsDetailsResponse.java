package com.anz.demo.accountmanagementservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsDetailsResponse {
    private long accountNumber;
    private String accountName;
    private String currency;
    private List<TransactionDto> transactionsList;

}
