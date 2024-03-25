package com.maximus.revenuesms.repositories;

import com.maximus.revenuesms.models.RevenueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RevenueRecordRepository extends JpaRepository<RevenueRecord, Long> {


    @Query(value = "SELECT SUM(amount) FROM revenue_record WHERE transaction_date BETWEEN :dateFrom AND :dateTo", nativeQuery = true)
    Double summarizeAmountsBetweenDates(LocalDate dateFrom, LocalDate dateTo);
}
