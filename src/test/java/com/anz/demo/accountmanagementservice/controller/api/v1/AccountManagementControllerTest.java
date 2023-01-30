package com.anz.demo.accountmanagementservice.controller.api.v1;

import com.anz.demo.accountmanagementservice.service.AccountManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountManagementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountManagementService accountManagementService;
    @Test
    void testGetAccountsDetailsByCustomerId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/"+ anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .header("transaction-id", "test")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testGetTransactionDetailsByAccountName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/"+ anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .header("transaction-id", "test")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
