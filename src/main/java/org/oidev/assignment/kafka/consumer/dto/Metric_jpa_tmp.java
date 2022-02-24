package org.oidev.assignment.kafka.consumer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Entity
@Table(name = "Metric")
public class Metric_jpa_tmp implements Serializable {
//    @Column(name="system_seq", )

    @Id
    @Column(name = "system_seq", nullable = false, columnDefinition = "varchar(10)")
    private String systemSeq;

    @Id
    @Column(name = "process_seq", columnDefinition = "varchar(10)")
    private String processSeq;

    @Id
    @Column(name = "metric_name", columnDefinition = "VARCHAR(50)")
    private String metricName;

    @Column(name = "table_name", columnDefinition = "VARCHAR(50)")
    private String tableName;

    @Column(name = "metric_value", columnDefinition = "VARCHAR(100)")
    private String metricValue;

    @Column(name = "timestamp", columnDefinition = "VARCHAR(10)")
    private String timestamp;

}
