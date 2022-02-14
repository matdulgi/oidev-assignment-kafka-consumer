package org.oidev.assignment.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.processor.CommonProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ConsumerService {

    @Autowired
    KafkaConsumer kafkaConsumer;
    @Autowired
    CommonProcessor commonProcessor;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    boolean keepRun = true;

    Queue<ConsumerRecord> queue = new LinkedList<>();

    public ConsumerService(){
    }

    public void run(){
        int i = 0;//
        while(keepRun) {
            System.out.println("start to consume : " + i++ );

//            ConsumerRecords consumerRecords = kafkaConsumer.poll(Long.MAX_VALUE);
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10000);

            for (ConsumerRecord<String, String> consumerRecord : consumerRecords){
                logger.info("count : " + consumerRecords.count());
                commonProcessor.process(consumerRecord);

            }
        }
    }

    public void stop(){
    }
}
