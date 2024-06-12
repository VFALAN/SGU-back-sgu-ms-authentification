package com.msc.ms.authentification.configuration.error;

import lombok.Builder;


public class RecordNoFound extends Exception {

    private final String entityClassName;
    private final Integer entityId;


    public RecordNoFound(String pEntityClassName, Integer pEntityId, String pMessage) {
        super(pMessage);
        this.entityClassName = pEntityClassName;
        this.entityId = pEntityId;
    }
}
