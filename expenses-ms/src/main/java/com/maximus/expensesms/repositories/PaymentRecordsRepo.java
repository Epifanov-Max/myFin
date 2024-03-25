package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PaymentRecordsRepo extends JpaRepository<PaymentRecord,Long> {

    /**
     * Получение суммы расходов между двумя датами
     * @param dateFrom дата начала периода
     * @param dateTo дата конца периода
     * @return сумма расходов
     */
    @Query(value = "SELECT SUM(amount) FROM expense_record WHERE transaction_date BETWEEN :dateFrom AND :dateTo", nativeQuery = true)
    Double summarizeAmountsBetweenDates(LocalDate dateFrom, LocalDate dateTo);

}
