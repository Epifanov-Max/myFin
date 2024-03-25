package com.maximus.expensesms.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


/** класс-сущность тип объектов */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "subject_type")
public class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** название типа объектов */
    @Column(name = "type_name", nullable = false)
    private String name;

    /** Примечание */
    private String comment;

    /**
     * Множество типов расходов сопоставляемое типам объектов
     * тип связи многие-ко-многим
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "subject_expense",  //таблица в БД соответствия id типов объектов и id типов расходов
            joinColumns = {@JoinColumn(name = "id_subject_type")},
            inverseJoinColumns = {@JoinColumn(name = "id_expense_type")})
    private Set<ExpenseType> expenseTypes = new HashSet<>();

    /** конструктор с 2-мя аргументами */
    public SubjectType(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
}
