package com.maximus.webms.models;

public enum Regularity {
    REGULAR("Регулярный"), NONREGULAR("Разовый");
    public String code;

    private Regularity(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
