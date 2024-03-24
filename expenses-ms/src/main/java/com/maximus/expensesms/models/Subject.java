package com.maximus.expensesms.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="subject_entity")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subj_name", nullable = false)
    private String name;

    private String address;
    private String note;

    /** Тип объекта */
    @ManyToOne
    @JoinColumn(name="id_type", referencedColumnName = "id", nullable = false)
    private SubjectType subjectType;




//    public Subject (String typeName){
//        this.typeName = typeName;
//    }

}

