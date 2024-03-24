package com.maximus.webms.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

public record ExpenseRecord (Long id, Long idExpenseCategory, Long idExpenseType, Long idSubjectType,
                             Long idSubject, String period, Double amount, String note,
                             LocalDate paymentDate, Date inputTime){

}
