package org.oidev.assignment.kafka.consumer.processor;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface RecordProcessor {
    public void process(ConsumerRecord<String, String> consumerRecord);
}
