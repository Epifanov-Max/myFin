package com.maximus.revenuesms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="revenue_type")
public class RevenueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Название типа доходов */
    @Column(name="type_name",nullable = false)
    private String name;

    /** примечание */
    private String note;

    public RevenueType(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public RevenueType(RevenueType revenueType) {
    }
}
