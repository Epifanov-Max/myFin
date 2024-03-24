package com.maximus.webms.services;

import com.maximus.webms.feignclients.WebRevenueFeignClient;
import com.maximus.webms.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("RevenueService")
public class WebRevenueService implements SumRecords{

    private final WebRevenueFeignClient webRevenueFeignClient;

    public List<RevenueRecord> getAllRevenueRecords(){
        return webRevenueFeignClient.getAllRevenueRecords();
    }

    public RevenueRecord getRevenueRecordById(Long id){
        return webRevenueFeignClient.getRevenueRecordById(id);
    }
    public Map<Long, String> mapPaymentStringRecords(){
        return webRevenueFeignClient.mapRevenueStringRecords();
    }

    public void addRevenueRecord(RevenueRecord revenueRecord) {
        webRevenueFeignClient.addRevenueRecord(revenueRecord);
    }
    public List<RevenueType> getAllRevenueTypes(){
        return webRevenueFeignClient.getAllRevenueTypes();
    }

    public void deleteRevenueRecord(Long id){
        webRevenueFeignClient.deleteRevenueRecord(id);
    }

    public Double getSumOfRecordsBetweenDates(LocalDate fromDate, LocalDate toDate){
        return webRevenueFeignClient.summarizeRevenueRecords(fromDate, toDate);
    }

    public void addRevenueType(RevenueType revenueType) {
            webRevenueFeignClient.addRevenueType(revenueType);
    }
    public RevenueType getRevenueTypeById(Long id){
        return webRevenueFeignClient.getRevenueTypeById(id);
    }

}
