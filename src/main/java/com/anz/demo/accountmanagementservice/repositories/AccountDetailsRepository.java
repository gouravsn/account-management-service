package com.anz.demo.accountmanagementservice.repositories;

import com.anz.demo.accountmanagementservice.entities.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, Long> {

    @Query(value = "select * from  account_management.account_details where customer_id = ?1", nativeQuery = true)
    List<AccountDetailsEntity> fetchAccountsByCustomerId(Long customerId);
}
