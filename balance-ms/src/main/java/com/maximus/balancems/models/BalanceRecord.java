package com.maximus.balancems.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Класс - сущность запись остатка
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "balance_record")
public class BalanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Сумма
     */
    private Double amount;

    /**
     * Заметка
     */
    private String note;

    /**
     * Дата транзакции
     */
    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate balanceDate;

    /**
     * дата внесения записи о транзакции
     */
    @Column(name = "input_time")
    private Date inputTime;
}
