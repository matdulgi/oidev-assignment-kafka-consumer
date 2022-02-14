package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.common.Core;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.processor.CommonProcessor;
import org.oidev.assignment.kafka.consumer.service.ConsumerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

import static com.dulgi.helper.common.Core.loadProps;

@Configuration
public class ConsumerConfiguration {
    private String consumerPropPath = "config/consumer.properties";
//    private KafkaConsumer kafkaConsumer;
    Set<String> topics = new HashSet<>();


    public ConsumerConfiguration() {
        setTopics();
//        setConsumerProp();
    }


    private void setTopics(){
        if(topics == null)
            topics = new HashSet<>();
        topics.add("METATRON_DATA");
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(loadProps(consumerPropPath));

        kafkaConsumer.subscribe(topics);
        return kafkaConsumer;
    }

    @Bean
    public ConsumerService consumerService(){
        return new ConsumerService();
    }

    @Bean
    public CommonProcessor commonProcessor(){
        return new CommonProcessor();
    }


}
