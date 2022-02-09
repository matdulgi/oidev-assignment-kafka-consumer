package org.oidev.assignment.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.ConfigValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ConsumerService {

    @Autowired
    KafkaConsumer kafkaConsumer;
    boolean keepRun = true;
    Set<String> Topics;

    public ConsumerService(){
    }

    public void run(){
        while(keepRun) {
            System.out.println("nanikaga hajimaru");
            ConfigValue configValue = new ConfigValue("");

            ConsumerRecords consumerRecords = kafkaConsumer.poll(Long.MAX_VALUE);

            consumerRecords.records("METATRON_DATA").forEach(r -> System.out.println(r));
//            consumerRecords.records("M");

        }
    }

    public void stop(){
        keepRun=false;
    }

}
