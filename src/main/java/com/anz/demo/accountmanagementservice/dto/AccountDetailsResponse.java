package com.anz.demo.accountmanagementservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDetailsResponse {

    private long customerId;
    private List<AccountDto> accountsList ;
}
