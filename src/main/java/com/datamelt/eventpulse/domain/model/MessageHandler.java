package com.datamelt.eventpulse.domain.model;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface MessageHandler
{
    void handleMessage(ConsumerRecord<String, String> record);
}
