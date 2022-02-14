package org.oidev.assignment.kafka.consumer.processor;

import com.dulgi.helper.common.CommonEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONArray;
import org.oidev.assignment.kafka.consumer.dto.Metric;

import java.util.List;
import java.util.Map;

/**
 * this class process the ConsumerRecord
 */
public class CommonProcessor implements Processor{

    public void process(ConsumerRecord<String,String> consumerRecord){
        List<Object> msgList = parseKafkaJson(consumerRecord);

        for(Object e : msgList){
            System.out.println(e);
//            System.out.println(obj.getClass().getSuperclass());
            Map<String, String> msg = (Map<String,String>)e;

            CommonEntity metric = new CommonEntity(Metric.class);
        }
    }

    public List<Object> parseKafkaJson(ConsumerRecord<String,String> consumerRecord){
        JSONArray jsonArray = new JSONArray(consumerRecord.value());
        return jsonArray.toList();
    }

    public void mapToDto(){

    }
}
