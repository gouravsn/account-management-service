package com.anz.demo.accountmanagementservice.controller.api.v1;


import com.anz.demo.accountmanagementservice.dto.AccountDetailsResponse;
import com.anz.demo.accountmanagementservice.dto.ErrorResponse;
import com.anz.demo.accountmanagementservice.dto.TransactionsDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Account Management Service", description = "Account Management APIs.")
@RequestMapping("/api/v1")
public interface IAccountManagementApi {


    @Operation(summary = "Fetch account list by customer Id.")
    @GetMapping(value = "/accounts/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data fetched successfully.",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = AccountDetailsResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unknown Internal Server Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    ResponseEntity<AccountDetailsResponse> getAccountListByCustomerId(
            @PathVariable(value = "customerId") long customerId,
            @RequestHeader(value = "transaction-id") String transactionId
    );

    @Operation(summary = "Fetch transactions by account number.")
    @GetMapping(value = "/transactions/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data fetched successfully.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransactionsDetailsResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unknown Internal Server Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    ResponseEntity<TransactionsDetailsResponse> getTransactionsByAccountNumber(
            @PathVariable(value = "accountNumber") long accountNumber,
            @RequestHeader(value = "transaction-id") String transactionId
    );
}
