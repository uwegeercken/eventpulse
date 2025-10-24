package com.datamelt.eventpulse.domain.service;

import com.datamelt.eventpulse.domain.model.MessageHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
public class DomainService implements MessageHandler
{
    @Override
    public void handleMessage(ConsumerRecord<String, String> record)
    {

    }
}
