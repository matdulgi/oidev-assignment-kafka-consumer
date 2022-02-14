package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.common.Core;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
