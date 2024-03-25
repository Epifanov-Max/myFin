package com.maximus.revenuesms.services;

import com.maximus.revenuesms.models.RevenueRecord;
import com.maximus.revenuesms.models.RevenueType;
import com.maximus.revenuesms.repositories.RevenueRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RevenueRecordService {

    private final RevenueRecordRepository revRecordRepo;
    private final RevenueTypeService revenueTypeService;

    public List<RevenueRecord> getAllRevenueRecords() {

        List<RevenueRecord> list = revRecordRepo.findAll();
        if (list.isEmpty()) {
            generateInitialDataExamples();
        }
        return list;
    }

    /** Генерация примера типов доходов */
    private void generateInitialDataExamples() {
        revenueTypeService.generateRevenueTypes();
    }


    public RevenueRecord getRevenueRecordById(Long id) {
        Optional<RevenueRecord> optRevenueRecord = revRecordRepo.findById(id);
        return optRevenueRecord.orElse(null);
    }

    public RevenueRecord addRevenueRecord(RevenueRecord revenueRecord) {
        if (revenueRecord.getInputTime() == null) {
            revenueRecord.setInputTime(new Date(System.currentTimeMillis()));
        }
        return revRecordRepo.save(revenueRecord);
    }

    public RevenueRecord updateRevenueRecord(Long id, RevenueRecord revenueRecordDetails) {
        Optional<RevenueRecord> optionalRevenueRecord = revRecordRepo.findById(id);
        if (optionalRevenueRecord.isPresent()) {
            RevenueRecord revenueRecord = optionalRevenueRecord.get();
            revenueRecord.setIdRevenueType(revenueRecordDetails.getIdRevenueType());
            revenueRecord.setNote(revenueRecordDetails.getNote());
            return revRecordRepo.save(revenueRecord);
        } else {
            throw new IllegalArgumentException("Запись доходов с id" + id + "не найдена");
        }
    }

    public void deleteRevenueRecord(Long id) {
        revRecordRepo.deleteById(id);
    }

    public Map<Long, String> recordsStringProcessing(List<RevenueRecord> revenueRecordList) {
        Map<Long, String> map = new HashMap<>();
        for (RevenueRecord revRec : revenueRecordList) {
            map.put(revRec.getId(), dataToStringMapping(revRec));
        }
        return map;
    }

    public String dataToStringMapping(RevenueRecord revenueRecord) {
        RevenueType revenueType = revenueTypeService.getRevenueTypeById(revenueRecord.getIdRevenueType());
        return revenueType.getName();
    }

    /**
     * Получение суммы доходов за период
     * @param dateFrom дата начала периоды
     * @param dateTo дата конца периода
     * @return
     */
    public Double summarizeRevenueRecords(LocalDate dateFrom, LocalDate dateTo){
        return revRecordRepo.summarizeAmountsBetweenDates(dateFrom, dateTo);
    }
}
