package com.maximus.balancems.controllers;

import com.maximus.balancems.models.BalanceRecord;
import com.maximus.balancems.services.BalanceRecordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BalanceRecordControllerTest {

    static List<BalanceRecord> balanceRecordList = new ArrayList<>();
    static BalanceRecord rec1,rec5,rec7;

    @Mock
    private BalanceRecordService service;

    @InjectMocks
    private BalanceRecordController controller;


    @BeforeAll
    static void init(){
        rec1 = new BalanceRecord(1L, 5000D, "note1", LocalDate.now(), new Date(System.currentTimeMillis()));
        rec5 = new BalanceRecord(5L, 10000D, "note5", LocalDate.now(), new Date(System.currentTimeMillis()));
        rec7 = new BalanceRecord(7L, 1000D, "note7", LocalDate.now(), new Date(System.currentTimeMillis()));
        balanceRecordList.add(rec1);
        balanceRecordList.add(rec5);
        balanceRecordList.add(rec7);
    }

    @Test
    void getAllBalanceRecordsWorksFine() {
        given(service.getAllBalanceRecords()).willReturn(balanceRecordList);
        controller.getAllBalanceRecords();
        verify(service).getAllBalanceRecords();
    }

    @Test
    void getBalanceRecordByIdWorksFine() {
        given(service.getBalanceRecordById(1L)).willReturn(rec1);
        BalanceRecord newRecord = controller.getBalanceRecordById(1L);
        assertEquals(newRecord.getAmount(), 5000);
    }

    @Test
    void updateBalanceRecordWorksFine() {
        BalanceRecord newRecordData = new BalanceRecord();
        newRecordData.setAmount(12345.00);
        rec5.setAmount(newRecordData.getAmount());

        given(service.updateBalanceRecord(5L, newRecordData)).willReturn(rec5);

        BalanceRecord updatedRecord = controller.updateBalanceRecord(5L,newRecordData);

        assertEquals(12345.00, updatedRecord.getAmount());
    }


    @Test
    public void updateBalanceRecordTestFail(){
        BalanceRecord newRecordData = new BalanceRecord();

        given(service.updateBalanceRecord(10L, newRecordData)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()-> controller.updateBalanceRecord(10L, newRecordData));
    }
}