import metatron.kafka.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("Main");
        logger.info("로그야로그그");

        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        if(applicationContext!=null){
            System.out.println("asdfiasdjf");
        }

        //applicationContext.getBean(KafkaConfig.class);
        KafkaConfig kafkaConfig = applicationContext.getBean(KafkaConfig.class);

                //new KafkaConfig();
        kafkaConfig.configConsumer();





    }

}
