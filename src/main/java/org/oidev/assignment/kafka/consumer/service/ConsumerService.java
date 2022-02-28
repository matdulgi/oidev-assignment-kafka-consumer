package org.oidev.assignment.kafka.consumer.service;

import com.dulgi.helper.annotation.NeedToChange;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.processor.MetatronProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.DoubleStream;

@Service
public class ConsumerService {
    KafkaConsumer kafkaConsumer;
    MetatronProcessor commonProcessor;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @NeedToChange("move to properties")
//    final int batchSize = 5;
    final int batchSize = 1;
    boolean isBatchMode = true;


    boolean keepRun = true;

    Queue<ConsumerRecord<String, String>> buffer = new LinkedList<>();

    public ConsumerService(){
        buffer = new LinkedList<>();
    }

    @Autowired
    public ConsumerService(KafkaConsumer kafkaConsumer, MetatronProcessor commonProcessor){
        this.kafkaConsumer = kafkaConsumer;
        this.commonProcessor = commonProcessor;
    }

    public void run(){
        int i = 0;//
        while(keepRun) {
            System.out.println("start to consume : " + i++ );

//            ConsumerRecords consumerRecords = kafkaConsumer.poll(Long.MAX_VALUE);
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10000);
            System.out.println("got count :" + consumerRecords.count());

            int k = 1;
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords){
                System.out.println(k++ + "th record");
//                logger.info("count : " + consumerRecords.count());

                if(isBatchMode){
                    buffer.add(consumerRecord);
                } else{
                    commonProcessor.process(consumerRecord);
                }
                if(isBatchMode && buffer.size() >= batchSize){
                    commonProcessor.process(buffer);
                    kafkaConsumer.commitAsync();
//                    buffer.clear();
                }

            }
        }
    }

    public void stop(){
    }
}
