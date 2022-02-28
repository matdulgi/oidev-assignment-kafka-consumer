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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * this class process the ConsumerRecord
 */
public class MetatronProcessor implements RecordProcessor, ApplicationContextAware {
    MetatronConsumerDAO metatronConsumerDAO;
//    private final int dtoBufferSize = 100;
    JDBCFunction jdbcFunction;
    @NeedToChange("cache created table")
    Set<String> tableDict;
    final String TABLE_DICT_PATH="config/tableDict";

    public MetatronProcessor(){
    }

    public MetatronProcessor(MetatronConsumerDAO metatronConsumerDAO, JDBCFunction jdbcFunction){
        this.metatronConsumerDAO = metatronConsumerDAO;
        this.jdbcFunction = jdbcFunction;
        if(tableDict== null){
            tableDict = new HashSet<>();
        }
        loadTableDict();
        tableDict.addAll(metatronConsumerDAO.searchTables());
    }

    private void loadTableDict() {
        File file = null;
        try {
            file = new ClassPathResource(TABLE_DICT_PATH).getFile();

            try ( FileReader fr  = new FileReader(file);
            ){
                int a = fr.read();
            } catch (IOException e) {
//            e.printStackTrace();
            } finally {
            }

        } catch (IOException e) {
            System.out.println("no table dictionary file");
        }
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
     *  - dto is stored in buffer
     * @param consumerRecord
     */
    public void process(ConsumerRecord<String,String> consumerRecord){
        List<Object> metricJsonList = parseKafkaJson(consumerRecord);
        Queue<Metric> metricList = new LinkedList<>();

        for(Object e : metricJsonList){
//            System.out.println(e);
            Map<String, String> metricMap = (Map<String,String>)e;
            metricList.add(getMetricDTO(metricMap));
        }

        //DAOInsertLogic
        metatronConsumerDAO.insertMetrics(metricList, tableDict);
        System.out.println("");
    }

    public List<Object> parseKafkaJson(ConsumerRecord<String,String> consumerRecord){
        return new JSONArray(consumerRecord.value()).toList();
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
        metric.setSystemSeq(Integer.parseInt(metricMap.get("system_seq")));
        metric.setProcessSeq(Integer.parseInt(metricMap.get("process_seq")));
        metric.setMetricName(metricMap.get("metric_name"));
        metric.setMetricValue(metricMap.get("metric_value"));
        metric.setTimestamp(Timestamp.valueOf(jdbcFunction.convertUnixTimeFormat(metricMap.get("timestamp"))));
        metric.setTableName(metricMap.get("table_name"));
        metric.setType(jdbcFunction.evalTypeRegex(metricMap.get("metric_value")));
        return metric;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
    }
}
