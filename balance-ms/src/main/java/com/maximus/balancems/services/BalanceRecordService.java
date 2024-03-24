package com.maximus.balancems.services;

import com.maximus.balancems.models.BalanceRecord;
import com.maximus.balancems.repositories.BalanceRecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceRecordService {

    private final BalanceRecordsRepository balanceRecordsRepo;

    public List<BalanceRecord> getAllBalanceRecords() {
        return balanceRecordsRepo.findAll();
    }

    public BalanceRecord getBalanceRecordById(Long id) {
        Optional<BalanceRecord> optBalanceRecord = balanceRecordsRepo.findById(id);
        return optBalanceRecord.orElse(null);
    }

    public BalanceRecord addBalanceRecord(BalanceRecord balanceRecord) {
        if (balanceRecord.getInputTime() == null) {
            balanceRecord.setInputTime(new Date(System.currentTimeMillis()));
        }
        return balanceRecordsRepo.save(balanceRecord);
    }

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

    public void deleteBalanceRecord(Long id) {
        balanceRecordsRepo.deleteById(id);
    }

    public BalanceRecord findLastBalanceRecord(LocalDate checkDate){
        return balanceRecordsRepo.findRecordByClosestTransactionDate(checkDate);
    }
}
