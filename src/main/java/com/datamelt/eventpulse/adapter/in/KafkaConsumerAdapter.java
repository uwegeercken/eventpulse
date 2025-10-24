package com.datamelt.eventpulse.adapter.in;

import com.datamelt.eventpulse.domain.model.MessageHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerAdapter
{
    private final MessageHandler messageHandler;

    public KafkaConsumerAdapter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }

    @KafkaListener(topics = "your-topic-name", groupId = "test-group")
    public void listen(ConsumerRecord<String, String> record)
    {
        messageHandler.handleMessage(record);
    }
}
