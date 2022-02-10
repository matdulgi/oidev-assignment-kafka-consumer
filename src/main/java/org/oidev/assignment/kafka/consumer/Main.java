package org.oidev.assignment.kafka.consumer;

import com.dulgi.helper.common.CommonEntity;
import org.oidev.assignment.kafka.consumer.service.ConsumerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        //tmp - basic properties
        String configClassPath = "org.oidev.assignment.kafka.consumer.config";

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(configClassPath);

        System.out.println(" --- print all bean's definition names ---");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(b -> System.out.println(b));

        ConsumerService consumerService = applicationContext.getBean(ConsumerService.class);
//        consumerService.run();

    }
}

