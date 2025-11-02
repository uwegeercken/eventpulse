package com.datamelt.eventpulse.utility.headers;

import com.datamelt.eventpulse.domain.model.HeaderKey;
import com.datamelt.eventpulse.domain.model.HeaderProcessingException;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

public class BusinessKeyValidator implements KeyValidator
{

    @Override
    public boolean validate(Headers headers) throws HeaderProcessingException
    {
        return isValidBusinessKey(headers);
    }

    private boolean isValidBusinessKey(Headers headers) throws HeaderProcessingException
    {
        Header header = headers.lastHeader(HeaderKey.BUSINESS_KEY.getKey());
        if (header != null && header.value() != null && header.value().length > 0)
        {
            return isValidBusinessKey(getStringValue(header.value()));
        }
        else
        {
            throw new HeaderProcessingException(String.format("header key [%s] not found or invalid value", HeaderKey.BUSINESS_KEY.getKey()));
        }
    }

    private static boolean isValidBusinessKey(String value)
    {
        if(value.length() < 5)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
