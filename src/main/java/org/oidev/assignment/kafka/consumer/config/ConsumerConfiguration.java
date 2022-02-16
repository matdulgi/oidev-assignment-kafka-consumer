package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.annotation.NeedToChange;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.processor.CommonProcessor;
import org.oidev.assignment.kafka.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

import static com.dulgi.helper.common.Core.loadProps;

@Configuration
public class ConsumerConfiguration {
    @NeedToChange("make parse properties logic, integrate all properties file")
    private String consumerPropPath = "config/consumer.properties";
//    private KafkaConsumer kafkaConsumer;
    Set<String> topics;

    public ConsumerConfiguration() {
        setTopics();
//        setConsumerProp();
    }


    @NeedToChange("move the list of topic to other location")
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
    public CommonProcessor commonProcessor(){
        return new CommonProcessor();
    }


}
