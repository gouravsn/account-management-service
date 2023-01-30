package com.anz.demo.accountmanagementservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name="account_details", schema = "account_management")
@Entity
@Getter
@Setter
public class AccountDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance_date")
    private LocalDate balanceDate;
    @Column(name = "currency")
    private String currency;
    @Column(name = "available_balance")
    private double availableBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private List<TransactionDetailsEntity> transactionDetails;
}
