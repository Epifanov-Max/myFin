package com.maximus.webms.feignclients;

import com.maximus.webms.models.BalanceRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Service
@FeignClient(name= "balance-records-service",url="http://127.0.0.1:8003" )
public interface WebBalanceFeignClient {

    @GetMapping("/balance/last-record")
    BalanceRecord getLastBalanceRecord(@RequestParam("checkDate") LocalDate checkDate);

    @GetMapping("/balance")
    List<BalanceRecord> getAllBalanceRecords();


    @PostMapping("/balance/save-balance-record")
    void addBalanceRecord(BalanceRecord balanceRecord);

    @DeleteMapping("/balance/{id}")
    void deleteBalanceRecord(@PathVariable("id") Long id);

    @GetMapping("/balance/{id}")
    BalanceRecord getBalanceRecordById(@PathVariable("id") Long paymentRecordId);
}
