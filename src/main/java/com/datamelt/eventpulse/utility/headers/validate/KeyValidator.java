package com.datamelt.eventpulse.utility.headers;

import com.datamelt.eventpulse.domain.model.HeaderProcessingException;
import com.datamelt.eventpulse.domain.model.TransactionType;
import org.apache.kafka.common.header.Headers;

public interface KeyValidator
{
    boolean validate(Headers headers) throws HeaderProcessingException;

    default String getStringValue(byte[] value) throws HeaderProcessingException
    {
        try
        {
            return new String(value);
        }
        catch (Exception ex)
        {
            throw new HeaderProcessingException(ex.getMessage());
        }
    }

    default int getIntegerValue(byte[] value) throws HeaderProcessingException
    {
        try
        {
            return Integer.parseInt(new String(value));
        }
        catch (Exception ex)
        {
            throw new HeaderProcessingException(ex.getMessage());
        }
    }

    default TransactionType getTransactionTypeValue(byte[] value) throws HeaderProcessingException
    {
        try
        {
            return TransactionType.valueOf(new String(value).toUpperCase());
        }
        catch (Exception ex)
        {
            throw new HeaderProcessingException(ex.getMessage());
        }
    }
}
