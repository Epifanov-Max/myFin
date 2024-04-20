package com.maximus.webms.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ExpenseRecordDTO {


    private Long id;

    private String expenseTypeName;

    private String subjectName;

    private String period;

    /** сумма оплаты */
    private Double amount;

    /** примечание */
    private String note;

    private String regularity;

    /**
     * дата транзакции
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    /**
     * дата внесения записи о транзакции
     */
    private Date inputTime;



}
