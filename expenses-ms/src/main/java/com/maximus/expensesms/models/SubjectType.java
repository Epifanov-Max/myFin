package com.maximus.expensesms.models;


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
@Table(name="subject_type")
public class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_name", nullable = false)
    private String name;

    private String comment;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "subject_expense",
            joinColumns = { @JoinColumn(name = "id_subject_type") },
            inverseJoinColumns = { @JoinColumn(name = "id_expense_type") })
//    @JsonIgnore
    private Set<ExpenseType> expenseTypes = new HashSet<>();

}
