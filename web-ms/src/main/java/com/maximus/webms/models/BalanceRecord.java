package com.maximus.webms.models;

import java.sql.Date;
import java.time.LocalDate;

public record BalanceRecord(Long id, Double amount, String note,  LocalDate balanceDate, Date inputTime) {
}
