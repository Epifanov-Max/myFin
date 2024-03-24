package com.maximus.webms.services;

import java.time.LocalDate;

public interface SumRecords {
    Double getSumOfRecordsBetweenDates(LocalDate fromDate, LocalDate toDate);
}
