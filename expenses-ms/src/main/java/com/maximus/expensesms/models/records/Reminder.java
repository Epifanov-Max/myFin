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
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expenseRecordId;

    private Periodicity periodicity;

    public Reminder(Long expenseRecordId, Periodicity periodicity, LocalDate nextDate1) {
        this.expenseRecordId = expenseRecordId;
        this.periodicity = periodicity;
        this.nextDate1 = nextDate1;
    }

    @Column(name = "input_time")
    private Date inputTime;

    @Column(name = "next_date1")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextDate1;

    @Column(name = "next_date2")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextDate2 = getNextDate2();

    LocalDate getNextDate2(){
        switch(periodicity){
            case WEEKLY: return nextDate1.plusWeeks(1);
            case MONTHLY: return nextDate1.plusMonths(1);
            case QUATERLY: return nextDate1.plusMonths(3);
            case HALFYEARLY:  return nextDate1.plusMonths(6);
            case YEARLY:  return nextDate1.plusYears(1);

            default: return null;
        }
    }


}
