package com.maximus.balancems.services;

import com.maximus.balancems.models.BalanceRecord;
import com.maximus.balancems.repositories.BalanceRecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Сервисный класс обработки записей остатка */
@Service
@RequiredArgsConstructor
public class BalanceRecordService {

    private final BalanceRecordsRepository balanceRecordsRepo;

    /**
     *Получение списка всех записей остатка
     * @return список записей остатка
     */
    public List<BalanceRecord> getAllBalanceRecords() {
         return balanceRecordsRepo.findAll();
    }

    /**
     * Получение записи остатка по ее id
     * @param id идентификатор
     * @return запись остатка
     */
    public BalanceRecord getBalanceRecordById(Long id) {
        Optional<BalanceRecord> optBalanceRecord = balanceRecordsRepo.findById(id);
        return optBalanceRecord.orElse(null);
    }

    /**
     * Добавить новую запись остатка
     */
    public BalanceRecord addBalanceRecord(BalanceRecord balanceRecord) {
        if (balanceRecord.getInputTime() == null) {
            balanceRecord.setInputTime(new Date(System.currentTimeMillis()));
        }
        return balanceRecordsRepo.save(balanceRecord);
    }

    /**
     * Обновление записи остатка по Id и полученным обновленным данным в виде объекта записи остатка.
     * @param id
     * @param balanceRecordDetails обновленные данные объекта записи остатка
     * @return
     */
    public BalanceRecord updateBalanceRecord(Long id, BalanceRecord balanceRecordDetails) {
        Optional<BalanceRecord> optionalBalanceRecord = balanceRecordsRepo.findById(id);
        if (optionalBalanceRecord.isPresent()) {
            BalanceRecord balanceRecord = optionalBalanceRecord.get();
            balanceRecord.setNote(balanceRecordDetails.getNote());
            return balanceRecordsRepo.save(balanceRecord);
        } else {
            throw new IllegalArgumentException("Запись баланса с id" + id + "не найдена");
        }
    }

    /**
     * Удаление записи остатка по id
     */
    public void deleteBalanceRecord(Long id) {
        balanceRecordsRepo.deleteById(id);
    }

    /**
     * Получение ближайшей к запрашиваемой дате записи остатка
     */
    public BalanceRecord findLastBalanceRecord(LocalDate checkDate){
        return balanceRecordsRepo.findRecordByClosestTransactionDate(checkDate);
    }
}
