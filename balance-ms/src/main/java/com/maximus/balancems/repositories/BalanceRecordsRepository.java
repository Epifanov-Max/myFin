package com.maximus.balancems.repositories;

import com.maximus.balancems.models.BalanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;

public interface BalanceRecordsRepository extends JpaRepository<BalanceRecord, Long> {

    @Query(value = "SELECT * FROM balance_record WHERE transaction_date <= :checkDate ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    BalanceRecord findRecordByClosestTransactionDate(LocalDate checkDate);



}
