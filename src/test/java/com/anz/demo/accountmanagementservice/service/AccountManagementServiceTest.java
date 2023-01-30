package com.anz.demo.accountmanagementservice.service;

import com.anz.demo.accountmanagementservice.dto.AccountDetailsResponse;
import com.anz.demo.accountmanagementservice.dto.TransactionsDetailsResponse;
import com.anz.demo.accountmanagementservice.entities.AccountDetailsEntity;
import com.anz.demo.accountmanagementservice.entities.CustomerEntity;
import com.anz.demo.accountmanagementservice.entities.TransactionDetailsEntity;
import com.anz.demo.accountmanagementservice.repositories.AccountDetailsRepository;
import com.anz.demo.accountmanagementservice.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountManagementServiceTest {
    
    private AccountManagementService accountManagementService;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    AccountDetailsRepository accountDetailsRepository;
    @Autowired
    public AccountManagementServiceTest(AccountManagementService accountManagementService){
        this.accountManagementService = accountManagementService;
    }
    
    @BeforeEach
    void setup(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerData()));
        when(accountDetailsRepository.findById(anyLong())).thenReturn(Optional.of(accountData()));
    }

    @Test
    void shouldGetAccountsDetailsByCustomerId(){
        AccountDetailsResponse accountDetailsResponse = accountManagementService
                .fetchAccountDetailsByCustomerId(anyLong());
        assertEquals(1, accountDetailsResponse.getAccountsList().size());
    }

    @Test
    void shouldGetTransactionsDetailsByAccountNumber(){
        TransactionsDetailsResponse transactionsDetailsResponse = accountManagementService
                .fetchTransactionsByAccountNumber(anyLong());
        assertEquals(234, transactionsDetailsResponse.getAccountNumber());
    }

    private AccountDetailsEntity accountData() {
        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setAccountNumber(234);
        accountDetailsEntity.setAccountType("Savings");
        List<TransactionDetailsEntity> transactionDetailsEntityList = new ArrayList<>();
        TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();
        transactionDetailsEntity.setTransactionId(8975);
        transactionDetailsEntityList.add(transactionDetailsEntity);
        accountDetailsEntity.setTransactionDetails(transactionDetailsEntityList);
        return accountDetailsEntity;
    }

    private CustomerEntity customerData() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(123);
        List<AccountDetailsEntity> accountDetailsEntityList = new ArrayList<>();
        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setAccountNumber(345);
        accountDetailsEntityList.add(accountDetailsEntity);
        customerEntity.setAccountDetailsEntityList(accountDetailsEntityList);
        return customerEntity;
    }

}
