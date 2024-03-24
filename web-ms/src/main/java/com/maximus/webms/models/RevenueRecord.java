package com.maximus.webms.models;


import java.sql.Date;
import java.time.LocalDate;

public record RevenueRecord(Long id, Long idRevenueType, Double amount, String note,
                            LocalDate revenueDate, Date inputTime){
}
