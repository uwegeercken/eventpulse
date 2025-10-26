package com.datamelt.eventpulse.domain.model;

public enum HeaderKeys
{
    BUSINESS_KEY("businessKey"),
    VERSION("version"),
    TRANSACTION("transaction");

    private final String value;

    HeaderKeys(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
