package service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Service
public class ConsumeService {
    Map kafkaConfig;

    ConsumeService(){
        System.out.println("Consumeservice 생성");
    }
    public void configConsume(String realpath) {
        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream("");
//        properties.load();

    }


    @Component
    class Config {
        String groupId;
        //    String port;
        String zookeeper;
        String bootstrapServer;
//        String contextPath= req.getServletContext().getContextPath();
//        System.out.println(contextPath);

        String testPath;

        {
            try {
                testPath = new ClassPathResource("kafka-config.xml").getFile().getAbsolutePath();
                System.out.println("testPath : " + testPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
