package com.maximus.webms.feignclients;

import com.maximus.webms.models.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient(name= "rev-records-service",url="http://127.0.0.1:8001" )
public interface WebRevenueFeignClient {

    @GetMapping("/revenues")
    List<RevenueRecord> getAllRevenueRecords();

    @GetMapping("/revenues/string-mapping")
    Map<Long, String> mapRevenueStringRecords();

    @PostMapping("/revenues/save-revenue-record")
    void addRevenueRecord(RevenueRecord revenueRecord);

    @GetMapping("/revenue-types")
    List<RevenueType> getAllRevenueTypes();

    @DeleteMapping("/revenues/{id}")
    void deleteRevenueRecord (@PathVariable("id") Long id);

    @GetMapping("/revenues/{id}")
    RevenueRecord getRevenueRecordById(@PathVariable("id") Long id);

    @GetMapping("/revenues/summarize")
    Double summarizeRevenueRecords(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate);

    @PostMapping("/revenue-types")
    void addRevenueType(RevenueType revenueType);

    @GetMapping("/revenue-types/{id}")
    RevenueType getRevenueTypeById(@PathVariable("id")Long id);
}
