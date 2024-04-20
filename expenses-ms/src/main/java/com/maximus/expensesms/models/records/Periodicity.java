package com.maximus.expensesms.models.records;

public enum Periodicity {

    WEEKLY("Еженедельно"),
    MONTHLY("Ежемесячно"),
    QUATERLY("Ежеквартально"),
    HALFYEARLY("Раз в полгода"),

    YEARLY("Ежегодно");


        private String code;

        private Periodicity(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }



