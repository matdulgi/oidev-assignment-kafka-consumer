package config;

import common.CommonFunctions;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
import service.ConsumerService;


@Configuration
public class ConsumerInit {
    private String consumerPropPath = "config/consumer.properties";
    private KafkaConsumer kafkaConsumer;

    public ConsumerInit() {
        System.out.println("call kafkaConfig default constructor");
        setConsumerProp();
    }


    //tryMethod determined by global variable
    //alternative methods are
    private void setConsumerProp() {
        kafkaConsumer = new KafkaConsumer(CommonFunctions.loadProperties(consumerPropPath));
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
