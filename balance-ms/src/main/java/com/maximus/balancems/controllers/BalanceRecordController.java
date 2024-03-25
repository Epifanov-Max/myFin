package com.maximus.balancems.controllers;

import com.maximus.balancems.models.BalanceRecord;
import com.maximus.balancems.services.BalanceRecordService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс контроллер записей остатка
 */
@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceRecordController {

    private final BalanceRecordService balanceRecordService;

    @GetMapping
    public List<BalanceRecord> getAllBalanceRecords() {
        return balanceRecordService.getAllBalanceRecords();
    }


    /**
     * Получение ближайшей по времени к запрашиваемой дате записи остатка
     * @param checkDate дата, от которой идет поиск
     * @return запись остатка
     */
    @GetMapping("/last-record")
    public BalanceRecord getLastBalanceRecord(@RequestParam("checkDate")LocalDate checkDate){
        return balanceRecordService.findLastBalanceRecord(checkDate);
    }

    /**
     * Получение записи остатка по id
     */
    @GetMapping("/{id}")
    public BalanceRecord getBalanceRecordById(@PathVariable("id") Long id){
        return balanceRecordService.getBalanceRecordById(id);
    }

    /**
     * добавление новой записи остатка
     */
    @PostMapping("/save-balance-record")
    public void addBalanceRecord(@RequestBody BalanceRecord balanceRecord) {
        balanceRecordService.addBalanceRecord(balanceRecord);
    }

    /**
     * Обновление записи остатка
     */
    @GetMapping("/update/{id}")
    public BalanceRecord updateBalanceRecord(@PathVariable Long id, @RequestBody BalanceRecord balanceRecord){
        return balanceRecordService.updateBalanceRecord(id, balanceRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        balanceRecordService.deleteBalanceRecord(id);
    }


}
