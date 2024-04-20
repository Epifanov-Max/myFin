package com.maximus.expensesms.models.records;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RegularityConverter implements AttributeConverter<Regularity, String> {

    @Override
    public String convertToDatabaseColumn(Regularity regularity) {
        if (regularity == null) {
            return null;
        }
        return regularity.getCode();
    }

    @Override
    public Regularity convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Regularity.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}