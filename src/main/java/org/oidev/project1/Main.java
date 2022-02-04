package org.oidev.project1;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ConsumerService;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.oidev.project1.config");

        System.out.println(" --- print all bean's definition names ---");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(b -> System.out.println(b));

        ConsumerService consumerService = applicationContext.getBean(ConsumerService.class);
        consumerService.run();

    }
}
