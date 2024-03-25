package com.maximus.expensesms.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Класс-сущность объект */

@NoArgsConstructor
@Data
@Entity
@Table(name="subject_entity")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Название объекта */
    @Column(name = "subj_name", nullable = false)
    private String name;

    /** Адрес, необязательное поле */
    private String address;

    /** Примечание  */
    private String note;

    /**
     * Тип объекта со связью (внешним ключом) с id объекта
     * связь типа многие-к-одному
     */
    @ManyToOne
    @JoinColumn(name="id_type", referencedColumnName = "id", nullable = false)
    private SubjectType subjectType;

    /** конструктор */
    public Subject(String name, String address, String note, SubjectType subjectType) {
        this.name = name;
        this.address = address;
        this.note = note;
        this.subjectType = subjectType;
    }
}

