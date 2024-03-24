package com.maximus.expensesms.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    private String comment;


}
