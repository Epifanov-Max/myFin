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

/** Контроллер типа доходов */
@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/revenues")
public class RevenueRecordsController {

    private final RevenueTypeService revenueTypeService;
    private final RevenueRecordService revenueRecordService;

    /** получение всех записей доходов */
    @GetMapping
    public List<RevenueRecord> getAllRevenueRecords() {
        return revenueRecordService.getAllRevenueRecords();
    }

    /**
     * Получение суммы записей доходов за период времени
     * @param fromDate дата начала периода
     * @param toDate дата конца периода
     * @return сумма
     */
    @GetMapping("/summarize")
    public Double summarizeRevenueRecords(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate){
       return revenueRecordService.summarizeRevenueRecords(fromDate, toDate);
    }

    /**
     * Получение записи дохода по id
     */
    @GetMapping("/{id}")
    public RevenueRecord getRevenueRecordById(@PathVariable("id") Long id){
        return revenueRecordService.getRevenueRecordById(id);
    }

    /**
     * Сохранение записи дохода
     * @param revenueRecord запись дохода
     */
    @PostMapping("/save-revenue-record")
    public void addRevenueRecord(@RequestBody RevenueRecord revenueRecord) {
        revenueRecordService.addRevenueRecord(revenueRecord);
    }

    /**
     * Обновление записи дохода по id
     * @param id id записи
     * @param revenueRecord запись дохода с новыми данными
     * @return обновленная запись дохода
     */
    @GetMapping("/update/{id}")
    public RevenueRecord updateRevenueRecord(@PathVariable Long id, @RequestBody RevenueRecord revenueRecord){
        return revenueRecordService.updateRevenueRecord(id, revenueRecord);
    }

    /** Удаление записи дохода по id */
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        revenueRecordService.deleteRevenueRecord(id);
    }

    /**
     * словарь
     * @return
     */
    @GetMapping("/string-mapping")
    public Map<Long, String> mapRevenueStringRecords(){
        return revenueRecordService. recordsStringProcessing(revenueRecordService.getAllRevenueRecords());
    }

}
