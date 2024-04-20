package com.maximus.expensesms.models.records;

public enum Regularity {
    REGULAR("Регулярный"), NONREGULAR("Разовый");
    private String code;

    private Regularity(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
