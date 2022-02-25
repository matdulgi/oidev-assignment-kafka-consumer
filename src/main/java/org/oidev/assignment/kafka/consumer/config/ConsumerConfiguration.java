package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.annotation.NeedToChange;
import com.dulgi.helper.jdbc.JDBCFunction;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.oidev.assignment.kafka.consumer.dao.MetatronConsumerDAO;
import org.oidev.assignment.kafka.consumer.processor.MetatronProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static com.dulgi.helper.common.Core.loadProps;

@Configuration
@Import(JdbcConfiguration.class)
//@DependsOn({"metatronConsumerDAO"})
//@DependsOn("jdbcConfiguration")
@ComponentScan(basePackages = "org.oidev.assignment.kafka.consumer.service")
public class ConsumerConfiguration {
    @NeedToChange("make parse properties logic, integrate all properties file")
    private String consumerPropPath = "config/consumer.properties";
//    private KafkaConsumer kafkaConsumer;
    Set<String> topics;

    MetatronConsumerDAO metatronConsumerDAO;
    JDBCFunction jdbcFunction;

    public ConsumerConfiguration() {
        setTopics();
    }

    @Autowired
    public ConsumerConfiguration(MetatronConsumerDAO metatronConsumerDAO, JDBCFunction jdbcFunction){
        setTopics();
        this.metatronConsumerDAO = metatronConsumerDAO;
        this.jdbcFunction = jdbcFunction;
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
    public MetatronProcessor metatronProcessor(){
        return new MetatronProcessor(metatronConsumerDAO, jdbcFunction);
    }


}
