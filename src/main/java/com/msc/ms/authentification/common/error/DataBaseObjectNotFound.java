package com.msc.ms.authentification.common.error;

import lombok.Getter;

@Getter
public class DataBaseObjectNotFound extends Exception {
    private final String field;
    private final String value;


    public DataBaseObjectNotFound(String pField, String message, String pValue) {
        super(message);
        field = pField;
        value = pValue;
    }
}
