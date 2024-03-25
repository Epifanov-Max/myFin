package com.maximus.expensesms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/** Класс-сущность категория затрат */
@Data
@Entity
@NoArgsConstructor
@Table(name="expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Название категории затрат */
    @Column(name="name", nullable = false)
    private String name;

    /** Комментарий */
    private String comment;

    public ExpenseCategory(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
}
