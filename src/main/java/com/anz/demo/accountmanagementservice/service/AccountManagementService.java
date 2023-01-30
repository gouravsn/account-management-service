package com.anz.demo.accountmanagementservice.service;

import com.anz.demo.accountmanagementservice.dto.AccountDetailsResponse;
import com.anz.demo.accountmanagementservice.dto.AccountDto;
import com.anz.demo.accountmanagementservice.dto.TransactionDto;
import com.anz.demo.accountmanagementservice.dto.TransactionsDetailsResponse;
import com.anz.demo.accountmanagementservice.entities.AccountDetailsEntity;
import com.anz.demo.accountmanagementservice.entities.CustomerEntity;
import com.anz.demo.accountmanagementservice.entities.TransactionDetailsEntity;
import com.anz.demo.accountmanagementservice.exception.handler.AccountServiceException;
import com.anz.demo.accountmanagementservice.repositories.AccountDetailsRepository;
import com.anz.demo.accountmanagementservice.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountManagementService {

    @Autowired
    AccountDetailsRepository accountDetailsRepository;

    @Autowired
    CustomerRepository customerRepository;

    public AccountDetailsResponse fetchAccountDetailsByCustomerId(long customerId) {
        Optional<CustomerEntity> customerEntity = customerRepository
                .findById(customerId);
        if(!customerEntity.isPresent()){
            throw new AccountServiceException("Customer id : "+customerId+" doesn't exist.");
        }
        AccountDetailsResponse accountDetailsResponse = null;
        if(!CollectionUtils.isEmpty(customerEntity.get().getAccountDetailsEntityList())) {
             accountDetailsResponse = prepareAccountDetailsResponse(customerEntity.get().getAccountDetailsEntityList(),
                     customerId);
        }else{
            throw new AccountServiceException("Customer id: "+customerId+" has no account.");
        }
        return accountDetailsResponse;
    }

    private AccountDetailsResponse prepareAccountDetailsResponse(List<AccountDetailsEntity> accountDetailsEntity,
                                                                 long customerId) {
        AccountDetailsResponse accountDetailsResponse = new AccountDetailsResponse();
        accountDetailsResponse.setCustomerId(customerId);
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(AccountDetailsEntity accountDetails : accountDetailsEntity){
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountName(accountDetails.getAccountName());
            accountDto.setAccountNumber(accountDetails.getAccountNumber());
            accountDto.setAccountType(accountDetails.getAccountType());
            accountDto.setCurrency(accountDetails.getCurrency());
            accountDto.setBalanceDate(accountDetails.getBalanceDate());
            accountDto.setAvailableBalance(accountDetails.getAvailableBalance());
            accountDtoList.add(accountDto);
        }
        accountDetailsResponse.setAccountsList(accountDtoList);
        return accountDetailsResponse;
    }

    public TransactionsDetailsResponse fetchTransactionsByAccountNumber(long accountNumber) {
        Optional<AccountDetailsEntity> accountDetailsEntity = accountDetailsRepository.findById(accountNumber);
        if(!accountDetailsEntity.isPresent()){
            throw new AccountServiceException("Account number : "+accountNumber+" doesn't exist.");
        }
        TransactionsDetailsResponse transactionsDetailsResponse = null;
        if(!CollectionUtils.isEmpty(accountDetailsEntity.get().getTransactionDetails())) {
            transactionsDetailsResponse = prepareTransactionDetailsResponse(accountDetailsEntity.get());
        }else{
            throw new AccountServiceException("Account number : "+accountNumber+" has no transaction.");
        }
        return transactionsDetailsResponse;
    }

    private TransactionsDetailsResponse prepareTransactionDetailsResponse(AccountDetailsEntity
                                                                                  accountDetails) {
        TransactionsDetailsResponse transactionsDetailsResponse = new TransactionsDetailsResponse();
        transactionsDetailsResponse.setAccountNumber(accountDetails.getAccountNumber());
        transactionsDetailsResponse.setAccountName(accountDetails.getAccountName());
        transactionsDetailsResponse.setCurrency(accountDetails.getCurrency());
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for(TransactionDetailsEntity transactionDetails: accountDetails.getTransactionDetails()){
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionId(transactionDetails.getTransactionId());
            transactionDto.setTransactionDate(transactionDetails.getTransactionDate());
            transactionDto.setTransactionAmount(transactionDetails.getTransactionAmount());
            transactionDto.setTransactionNarrative(transactionDetails.getTransactionNarrative());
            transactionDto.setTransactionType(transactionDetails.getTransactionType());
            transactionDtoList.add(transactionDto);
        }
        transactionsDetailsResponse.setTransactionsList(transactionDtoList);
        return transactionsDetailsResponse;
    }
}

