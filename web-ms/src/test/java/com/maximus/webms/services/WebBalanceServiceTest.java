package com.maximus.webms.services;

import com.maximus.webms.feignclients.WebBalanceFeignClient;
import com.maximus.webms.feignclients.WebExpenseFeignClient;
import com.maximus.webms.feignclients.WebRevenueFeignClient;
import com.maximus.webms.models.BalanceRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WebBalanceServiceTest {



    @Mock
    WebBalanceFeignClient feignClient;

    @Mock
    @Qualifier("RevenueService")
     SumRecords sumRecordsRevenue;

    @Mock
    @Qualifier("ExpenseService")
     SumRecords sumRecordsExpense;

    @MockBean
    private WebBalanceService service;

    @Test
    void getBalanceOfOperationsBetweenDatesNoBalanceRecordTest() {
        BalanceRecord newAutoRecord = new BalanceRecord(1L, 0D,
                "Автоматическое внесение на начало работы программы",
                LocalDate.parse("1900-01-01"), null);
        String fromDate = "2023-08-31";
        String checkDate = "2023-12-12";
        LocalDate newFromDate = LocalDate.parse(fromDate);
        LocalDate newCheckDate = LocalDate.parse(checkDate);
//        given(feignClient.getLastBalanceRecord(newCheckDate)).willReturn(null);
        given(feignClient.getLastBalanceRecord(newCheckDate)).willReturn(newAutoRecord);
        given(sumRecordsRevenue.getSumOfRecordsBetweenDates(newFromDate, newCheckDate)).willReturn(50000D);
        given(sumRecordsExpense.getSumOfRecordsBetweenDates(newFromDate, newCheckDate)).willReturn(30000D);

        Double result = service.getBalanceOfOperationsBetweenDates(newFromDate,newCheckDate);
//        verify(feignClient,times(2)).getLastBalanceRecord(newCheckDate);
        verify(sumRecordsRevenue).getSumOfRecordsBetweenDates(newFromDate, newCheckDate);
//        assertEquals(20000.00, result);




    }

    @Test
    void getRevenueSumTest() {
    }

    @Test
    void getExpenseSumTest() {
    }

    @Test
    void addAutoCorrectionRecordTest() {
    }
}