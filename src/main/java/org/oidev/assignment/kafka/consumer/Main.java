package org.oidev.assignment.kafka.consumer;

import org.oidev.assignment.kafka.consumer.service.ConsumerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Main {
    static String configClassPath = "org.oidev.assignment.kafka.consumer.config";

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        /**
         * arg1 :
         * arg2 :
        */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(configClassPath);
        //tmp - basic properties
        System.out.println();


//        System.out.println(" --- print all bean's definition names ---");
//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(b -> System.out.println(b));

        ConsumerService consumerService = applicationContext.getBean(ConsumerService.class);
        consumerService.run();
    }
}
