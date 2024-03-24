package com.maximus.revenuesms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name="revenue_record")
public class RevenueRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_type", nullable = false)
    private Long idRevenueType;

    private Double amount;
    private String note;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate revenueDate;

    @Column(name = "input_time")
    private Date inputTime;
}
