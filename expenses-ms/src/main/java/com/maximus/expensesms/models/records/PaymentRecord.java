package com.maximus.expensesms.models.records;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "expense_record")
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * id категории расходов
     */
    @Column(name = "id_exp_cat", nullable = false)
    private Long idExpenseCategory;

    /**
     * id типа расходов
     */
    @Column(name = "id_exp_type", nullable = false)
    private Long idExpenseType;

    /**
     * id типа объектов
     */
    @Column(name = "id_subject_type", nullable = false)
    private Long idSubjectType;

    /**
     * id объекта
     */
    @Column(name = "id_subject", nullable = false)
    private Long idSubject;

    /** период за который производится оплата */
    private String period;

    /** сумма оплаты */
    private Double amount;

    /** примечание */
    private String note;

    /** регулярность платежа */
    private Regularity regularity;

    /**
     * дата транзакции
     */
    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    /**
     * дата внесения записи о транзакции
     */
    @Column(name = "input_time")
    private Date inputTime;
}
