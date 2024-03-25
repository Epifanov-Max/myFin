package com.maximus.balancems.repositories;

import com.maximus.balancems.models.BalanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/** Интерфейс репозиторий записей остатка */
@Repository
public interface BalanceRecordsRepository extends JpaRepository<BalanceRecord, Long> {

    /**
     * Получение ближайшей к запрашиваемой дате записи остатка
     * @param checkDate запрашиваемая дата
     * @return запись остатка
     */
    @Query(value = "SELECT * FROM balance_record WHERE transaction_date <= :checkDate ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    BalanceRecord findRecordByClosestTransactionDate(LocalDate checkDate);



}
