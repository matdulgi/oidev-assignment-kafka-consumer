package org.oidev.assignment.kafka.consumer.processor;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface Processor {
    public void process(ConsumerRecord<String, String> consumerRecord);
}
