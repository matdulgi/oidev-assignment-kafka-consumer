package org.oidev.assignment.kafka.consumer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class Metric implements Serializable {

    private Integer systemSeq;

    private Integer processSeq;

    private String metricName;

    private String metricValue;

    private Timestamp timestamp;

    private String type;

    private String tableName;

}
