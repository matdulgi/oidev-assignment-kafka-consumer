package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.common.Core;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration

@Import({JdbcConfiguration.class, ConsumerConfiguration.class})
public class CommonConfiguration {


    @Bean
    public Core core(){
        return new Core();
    }

    @Bean
    public Metric metric(){
        return new Metric();
    }
}
