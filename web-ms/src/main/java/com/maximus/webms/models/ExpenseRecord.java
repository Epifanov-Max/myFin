package com.maximus.webms.models;


import java.sql.Date;
import java.time.LocalDate;

public record ExpenseRecord (Long id, Long idExpenseCategory, Long idExpenseType, Long idSubjectType,
                             Long idSubject, String period, Double amount, String note, Regularity regularity,
                             LocalDate paymentDate, Date inputTime){

}
