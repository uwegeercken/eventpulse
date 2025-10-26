package com.datamelt.eventpulse.utility.headers;

import java.util.function.Function;

public class MessageMetadataValidator
{
    public static Function<String,Boolean> validBusinessKey = MessageMetadataValidator::isValidBusinessKey;
    public static Function<Integer,Boolean> validVersion = MessageMetadataValidator::isValidVersion;

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
}
