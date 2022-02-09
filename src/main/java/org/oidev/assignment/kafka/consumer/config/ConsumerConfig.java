package org.oidev.assignment.kafka.consumer.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.service.ConsumerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ConsumerConfig {
    private String consumerPropPath = "config/consumer.properties";
    private KafkaConsumer kafkaConsumer;

    public ConsumerConfig() {
        System.out.println("call kafkaConfig default constructor");
        setConsumerProp();
    }


    //tryMethod determined by global variable
    //alternative methods are
    private void setConsumerProp() {
        // kafkaConsumer = new KafkaConsumer(CommonFunctions.loadProperties(consumerPropPath));
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        return kafkaConsumer;
    }

    @Bean
    public ConsumerService consumerService(){
        ConsumerService consumerService = new ConsumerService();
        return consumerService;
    }


}
