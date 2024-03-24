package com.maximus.expensesms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name="expense_record")
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_exp_cat", nullable = false)
    private Long idExpenseCategory;

    @Column(name = "id_exp_type", nullable = false)
    private Long idExpenseType;

    @Column(name = "id_subject_type", nullable = false)
    private Long idSubjectType;

    @Column(name = "id_subject", nullable = false)
    private Long idSubject;

    private String period;
    private Double amount;
    private String note;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(name = "input_time")
    private Date inputTime;
}
