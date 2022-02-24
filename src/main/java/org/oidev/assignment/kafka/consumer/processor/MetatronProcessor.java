package org.oidev.assignment.kafka.consumer.processor;

import com.dulgi.helper.annotation.NeedToChange;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONArray;
import org.oidev.assignment.kafka.consumer.dao.MetatronConsumerDAO;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

/**
 * this class process the ConsumerRecord
 */
public class MetatronProcessor implements Processor, ApplicationContextAware {
    MetatronConsumerDAO kafkaConsumerDao;
    Queue<Metric> dtoQueue = new LinkedList<>();

    public MetatronProcessor(){
    }

    @Autowired
    public MetatronProcessor(MetatronConsumerDAO kafkaConsumerDao){
        this.kafkaConsumerDao = kafkaConsumerDao;

    }

    @NeedToChange("polymorphism for dto")
    public void process(Queue<ConsumerRecord<String, String>> buffer){
        for(ConsumerRecord consumerRecord : buffer){
            process(consumerRecord);
        }
    }

    public void process(ConsumerRecord<String,String> consumerRecord){
        System.out.println("sogood");
        List<Object> msgList = parseKafkaJson(consumerRecord);

        for(Object e : msgList){
            System.out.println(e);
//            System.out.println(obj.getClass().getSuperclass());
            Map<String, String> metricMap = (Map<String,String>)e;

            dtoQueue.add(getMetricDTO(metricMap));
            //DAOInsertLogic
        }
        System.out.println("tmp");
    }

    public List<Object> parseKafkaJson(ConsumerRecord<String,String> consumerRecord){
        JSONArray jsonArray = new JSONArray(consumerRecord.value());
        return jsonArray.toList();
    }

//    public void mapToDto(Map<String, Object> map){
//        @NeedToChange("consider to change to getBean with container")
//        DTOEntity<Metric> dtoEntity = new DTOEntity<>(Metric.class);
//        dtoEntity.initEntity(map);
//        System.out.println("DTO INfo : "  + ((Metric)dtoEntity.getEntity()).toString());
//        dtoQueue.add((Metric) dtoEntity.getEntity());

    public Metric getMetricDTO(Map<String, String> metricMap){
        @NeedToChange("apply reflection")
        Metric metric = new Metric();
        metric.setSystemSeq(metricMap.get("system_seq"));
        metric.setProcessSeq(metricMap.get("process_seq"));
        metric.setMetricName(metricMap.get("metric_name"));
        metric.setMetricValue(metricMap.get("metric_value"));
        metric.setTimestamp(metricMap.get("timestamp"));
        return   metric;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
    }
}
