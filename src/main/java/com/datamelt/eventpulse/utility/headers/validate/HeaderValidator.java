package com.datamelt.eventpulse.utility.headers;

import com.datamelt.eventpulse.domain.model.HeaderKey;
import com.datamelt.eventpulse.domain.model.HeaderProcessingException;
import com.datamelt.eventpulse.domain.model.TransactionType;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HeaderValidator
{
    public static Predicate<Headers> validateHeaders = HeaderValidator::validate;

    private static boolean validate(Headers headers) throws HeaderProcessingException
    {
        if(!allMandatoryKeysFound(headers))
        {
            throw new HeaderProcessingException(String.format("message headers must contain keys [%s]", HeaderKey.getAllKeys()));
        }
        else
        {
            BusinessKeyValidator businessKeyValidator = new BusinessKeyValidator();

            KeyValidator x = headers1 -> true;

                    x.validate(headers);

            businessKeyValidator.validate(headers);
            return isValidBusinessKey(headers)
                    && isValidVersion(headers)
                    && isValidTransactionType(headers);
        }
    }

    private static boolean allMandatoryKeysFound(Headers headers)
    {
        Set<String> allHeaderKeys = getAllHeadersKeys(headers);
        Set<String> mandatoryHeaderKeys = HeaderKey.getKeyset();
        return allHeaderKeys.containsAll(mandatoryHeaderKeys);
    }

    private static Set<String> getAllHeadersKeys(Headers headers)
    {
        return Arrays.stream(headers.toArray())
                .map(Header::key)
                .collect(Collectors.toSet());
    }



    private static boolean isValidVersion(Headers headers) throws HeaderProcessingException
    {
        Header header = headers.lastHeader(HeaderKey.VERSION.getKey());
        if (header != null && header.value() != null && header.value().length > 0)
        {
            return isValidVersion(getIntegerValue(header.value()));
        }
        else
        {
            throw new HeaderProcessingException(String.format("header key [%s] not found or invalid value", HeaderKey.BUSINESS_KEY.getKey()));
        }
    }

    private static boolean isValidVersion(int value)
    {
        if(value < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private static boolean isValidTransactionType(Headers headers) throws HeaderProcessingException
    {
        Header header = headers.lastHeader(HeaderKey.TRANSACTION.getKey());
        if (header != null && header.value() != null && header.value().length > 0)
        {
            TransactionType transactionType = getTransactionTypeValue(header.value());
            return true;
        }
        else
        {
            throw new HeaderProcessingException(String.format("header key [%s] not found or invalid value", HeaderKey.BUSINESS_KEY.getKey()));
        }
    }


}
