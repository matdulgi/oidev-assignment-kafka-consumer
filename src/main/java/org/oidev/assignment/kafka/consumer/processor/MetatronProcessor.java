package org.oidev.assignment.kafka.consumer.processor;

import com.dulgi.helper.annotation.NeedToChange;
import com.dulgi.helper.jdbc.JDBCFunction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONArray;
import org.oidev.assignment.kafka.consumer.dao.MetatronConsumerDAO;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

/**
 * this class process the ConsumerRecord
 */
public class MetatronProcessor implements Processor, ApplicationContextAware {
    MetatronConsumerDAO metatronConsumerDAO;
    Queue<Metric> dtoQueue = new LinkedList<>();
//    private final int dtoBufferSize = 100;
    JDBCFunction jdbcFunction;

    public MetatronProcessor(){
    }

    public MetatronProcessor(MetatronConsumerDAO metatronConsumerDAO, JDBCFunction jdbcFunction){
        this.metatronConsumerDAO = metatronConsumerDAO;
        this.jdbcFunction = jdbcFunction;
    }

    @NeedToChange("polymorphism for dto")
    public void process(Queue<ConsumerRecord<String, String>> buffer){
        for(ConsumerRecord consumerRecord : buffer){
            process(consumerRecord);
        }
    }

    /**
     * parameter ConsumerRecord includes the list of json per every metric message
     *  loop
     *  - parse each json and convert to map
     *  - convert map to dto
     *  - dto is saved to buffer
     * @param consumerRecord
     */
    public void process(ConsumerRecord<String,String> consumerRecord){
        List<Object> metricJsonList = parseKafkaJson(consumerRecord);

        for(Object e : metricJsonList){
            System.out.println(e);
//            System.out.println(obj.getClass().getSuperclass());
            Map<String, String> metricMap = (Map<String,String>)e;

            dtoQueue.add(getMetricDTO(metricMap));

        }
        //DAOInsertLogic
        metatronConsumerDAO.insertMetrics(dtoQueue);
        System.out.println("");
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
        metric.setTableName(metricMap.get("table_name"));
        metric.setType(jdbcFunction.evalTypeRegex(metricMap.get("metric_value")));
        return metric;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
    }
}
