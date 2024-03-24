package com.maximus.expensesms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="expense_type")
public class ExpenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="id_exp", referencedColumnName = "id", nullable = false)
    private ExpenseCategory expenseCategory;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "expenseTypes")
    @JsonIgnore
    private Set<SubjectType> subjectTypes = new HashSet<>();

    public void addSubjectType(SubjectType subjectType){
        subjectTypes.add(subjectType);
        subjectType.getExpenseTypes().add(this);
    }
}
