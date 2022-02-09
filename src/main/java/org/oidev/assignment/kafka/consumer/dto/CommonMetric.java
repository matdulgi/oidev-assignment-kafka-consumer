package org.oidev.assignment.kafka.consumer.dto;

public class CommonMetric {
    private String system_seq;
    private String process_seq;
    private String metric_name;
    private String table_name;
    private String metric_value;
    private String timestamp;

    public String getSystem_seq() {
        return system_seq;
    }
    public void setSystem_seq(String system_seq) {
        this.system_seq = system_seq;
    }
    public String getProcess_seq() {
        return process_seq;
    }
    public void setProcess_seq(String process_seq) {
        this.process_seq = process_seq;
    }
    public String getMetric_name() {
        return metric_name;
    }
    public void setMetric_name(String metric_name) {
        this.metric_name = metric_name;
    }
    public String getTable_name() {
        return table_name;
    }
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    public String getMetric_value() {
        return metric_value;
    }
    public void setMetric_value(String metric_value) {
        this.metric_value = metric_value;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
