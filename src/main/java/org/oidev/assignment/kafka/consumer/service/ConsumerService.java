package org.oidev.assignment.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.processor.CommonProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsumerService {
    KafkaConsumer kafkaConsumer;
    CommonProcessor commonProcessor;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    boolean keepRun = true;

    Queue<ConsumerRecord> queue = new LinkedList<>();

    public ConsumerService(){ }

    @Autowired
    public ConsumerService(KafkaConsumer kafkaConsumer, CommonProcessor commonProcessor){
        this.kafkaConsumer = kafkaConsumer;
        this.commonProcessor = commonProcessor;
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
