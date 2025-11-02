package com.datamelt.eventpulse.utility.headers;

import com.datamelt.eventpulse.utility.MessageMetadataValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageMetadataValidatorTest
{
    @Test
    void belowMinimumBusinessKeyFailsValidation()
    {
        String businessKey = "ABC";

        assertFalse(MessageMetadataValidator.validBusinessKey.apply(businessKey));
    }

    @Test
    void minimumLengthBusinessKeyPassesValidation()
    {
        String businessKey = "ABCDE";

        assertTrue(MessageMetadataValidator.validBusinessKey.apply(businessKey));
    }

    @Test
    void minimumVersionPassesValidation()
    {
        int version = 0;

        assertTrue(MessageMetadataValidator.validVersion.apply(version));
    }

    @Test
    void belowMinimumVersionFailsValidation()
    {
        int version = -1;

        assertFalse(MessageMetadataValidator.validVersion.apply(version));
    }
}