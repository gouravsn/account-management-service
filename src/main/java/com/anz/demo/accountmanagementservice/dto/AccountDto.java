package com.anz.demo.accountmanagementservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDto {
    private long accountNumber;
    private String accountName;
    private String accountType;
    private LocalDate balanceDate;
    private String currency;
    private double availableBalance;
}
