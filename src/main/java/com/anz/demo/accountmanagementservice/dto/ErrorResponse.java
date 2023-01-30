package com.anz.demo.accountmanagementservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ErrorResponse {
    private String transactionId;
    private String errorText;
    private ZonedDateTime responseTime;
}
