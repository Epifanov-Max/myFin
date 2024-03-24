package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PaymentRecordsRepo extends JpaRepository<PaymentRecord,Long> {

    @Query(value = "SELECT SUM(amount) FROM expense_record WHERE transaction_date BETWEEN :dateFrom AND :dateTo", nativeQuery = true)
    Double summarizeAmountsBetweenDates(LocalDate dateFrom, LocalDate dateTo);

}
