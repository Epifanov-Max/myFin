package com.maximus.revenuesms.controllers;


import com.maximus.revenuesms.models.RevenueRecord;
import com.maximus.revenuesms.services.RevenueTypeService;
import com.maximus.revenuesms.services.RevenueRecordService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/revenues")
public class RevenueRecordsController {

    private final RevenueTypeService revenueTypeService;
    private final RevenueRecordService revenueRecordService;

    @GetMapping
    public List<RevenueRecord> getAllRevenueRecords() {
        return revenueRecordService.getAllRevenueRecords();
    }

    @GetMapping("/summarize")
    public Double summarizeRevenueRecords(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate){
       return revenueRecordService.summarizeRevenueRecords(fromDate, toDate);
    }


    @GetMapping("/{id}")
    public RevenueRecord getRevenueRecordById(@PathVariable("id") Long id){
        return revenueRecordService.getRevenueRecordById(id);
    }

    @PostMapping("/save-revenue-record")
    public void addRevenueRecord(@RequestBody RevenueRecord revenueRecord) {
        revenueRecordService.addRevenueRecord(revenueRecord);
    }

    @GetMapping("/update/{id}")
    public RevenueRecord updateRevenueRecord(@PathVariable Long id, @RequestBody RevenueRecord revenueRecord){
        return revenueRecordService.updateRevenueRecord(id, revenueRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        revenueRecordService.deleteRevenueRecord(id);
    }

    @GetMapping("/string-mapping")
    public Map<Long, String> mapRevenueStringRecords(){
        return revenueRecordService. recordsStringProcessing(revenueRecordService.getAllRevenueRecords());
    }

}
