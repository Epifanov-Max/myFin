package com.maximus.expensesms.models.records;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;


@Converter(autoApply = true)
public class PeriodicityConverter implements AttributeConverter<Periodicity, String> {

    @Override
    public String convertToDatabaseColumn(Periodicity periodicity) {
        if (periodicity == null) {
            return null;
        }
        return periodicity.getCode();
    }

    @Override
    public Periodicity convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Periodicity.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
