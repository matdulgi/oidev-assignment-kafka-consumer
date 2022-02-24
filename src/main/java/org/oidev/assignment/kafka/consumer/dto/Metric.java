package org.oidev.assignment.kafka.consumer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Metric implements Serializable {

    private String systemSeq;

    private String processSeq;

    private String metricName;

    private String metricValue;

    private String timestamp;

    private String type;

}
