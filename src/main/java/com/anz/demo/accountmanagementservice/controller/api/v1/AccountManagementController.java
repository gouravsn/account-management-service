package com.anz.demo.accountmanagementservice.controller.api.v1;

import com.anz.demo.accountmanagementservice.dto.AccountDetailsResponse;
import com.anz.demo.accountmanagementservice.dto.TransactionsDetailsResponse;
import com.anz.demo.accountmanagementservice.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountManagementController implements IAccountManagementApi{

    @Autowired
    AccountManagementService accountManagementService;
    @Override
    public ResponseEntity<AccountDetailsResponse> getAccountListByCustomerId(long customerId, String transactionId) {
        AccountDetailsResponse accountDetailsResponse = accountManagementService.fetchAccountDetailsByCustomerId(customerId);
        return ResponseEntity.ok().body(accountDetailsResponse);
    }

    @Override
    public ResponseEntity<TransactionsDetailsResponse> getTransactionsByAccountNumber(long accountNumber, String transactionId) {
        TransactionsDetailsResponse transactionsDetailsResponse = accountManagementService
                .fetchTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok().body(transactionsDetailsResponse);
    }
}
