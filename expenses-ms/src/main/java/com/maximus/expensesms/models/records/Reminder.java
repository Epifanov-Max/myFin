package com.maximus.expensesms.models.records;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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

    @Column(name = "expense_record_id", nullable = false)
    private Long expenseRecordId;

    @Column(name = "periodicity", nullable = false)
    private Periodicity periodicity;

//    public Reminder(Long expenseRecordId, Periodicity periodicity, LocalDate nextDate1) {
//        this.expenseRecordId = expenseRecordId;
//        this.periodicity = periodicity;
//        this.nextDate1 = nextDate1;
//        this.nextDate2 = putNextDate2(nextDate1);
//
//    }

    @Column(name = "input_time")
    private Date inputTime;

    private String subjectName;

    private String expenseTypeName;

    @Column(name = "next_date1")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextDate1;

    @Column(name = "next_date2")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextDate2;  //= putNextDate2(nextDate1);

    public void setNextDate2() {
        this.nextDate2 = putNextDate2();
    }

    LocalDate putNextDate2() {
        if (nextDate1 != null) {
            return switch (periodicity) {
                case WEEKLY -> nextDate1.plusWeeks(1);
                case MONTHLY -> nextDate1.plusMonths(1);
                case QUATERLY -> nextDate1.plusMonths(3);
                case HALFYEARLY -> nextDate1.plusMonths(6);
                case YEARLY -> nextDate1.plusYears(1);

            };
        } else {
            return null;
        }
    }


}
