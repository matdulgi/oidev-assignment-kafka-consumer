package org.oidev.assignment.kafka.consumer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.dulgi.helper.entity.ConfigurationEntity;
import com.dulgi.helper.jdbc.JDBCFunction;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan (basePackages = "org.oidev.assignment.kafka.consumer.dao")
public class JdbcConfiguration{
    Logger logger = LoggerFactory.getLogger(JdbcConfiguration.class);

    private Properties jdbcProps;
    private File propFile;
    private DataSource dataSource;
    private String propFilePath = "config/jdbc.properties";
    private Class<BasicDataSource> dataSourceClass = BasicDataSource.class;

    public JdbcConfiguration(){
        try {
            initJdbcProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initJdbcProps() throws IOException{
        logger.info("jdbc properties file path : " + propFilePath);
        jdbcProps = new Properties();
        propFile = new ClassPathResource(propFilePath).getFile();
        try (FileInputStream fis = new FileInputStream(propFile)){
            jdbcProps.load(fis);
       }
    }

    @Bean
    public Properties jdbcProps(){
       return jdbcProps;
    }
    // to do : DataSource Polymorphism by genefic & reflection
    // condition 1. DataSource can be changed
    // condition 2. set Method reflection
    // conditnon 3. 

    // public <T> T datasource(Class<? extends DataSource> clazz){
    // public <T> T datasource(){
    //     Class<T> clazz =  (Class<T>)((ParameterizedType)getClass().getGenericSuperclass())
    //     Method currentMethod = new Object(){}.getClass().getEnclosingMethod();
    //     TypeVariable type = currentMethod.getGenericReturnType();
    //     T dataSource = 

    //     return new
    // }
    
    @Bean
    public BasicDataSource basicDataSource(){
//        CommonEntity datsSourceEntity = new DataSourceEntity(BasicDataSource.class);
        ConfigurationEntity<BasicDataSource> entity = new ConfigurationEntity<>(BasicDataSource.class, jdbcProps);
        entity.initEntity(jdbcProps);
        BasicDataSource dataSource = (BasicDataSource) entity.getEntity();
        entity.entityInfo();
        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(basicDataSource());
        return jdbcTemplate;
    }

    @Bean
    public JDBCFunction jdbcFunction(){
        return new JDBCFunction("mysql");
    }

}
