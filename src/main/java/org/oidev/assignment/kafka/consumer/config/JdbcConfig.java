package org.oidev.assignment.kafka.consumer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JdbcConfig {
    Logger logger = LoggerFactory.getLogger(JdbcConfig.class);

    private Properties jdbcProps=null;
    private File propFile;
    private DataSource dataSource;
    private String propFilePath = "config/jdbc.propeties";
    private Class<BasicDataSource> dataSourceClass = BasicDataSource.class;

    public JdbcConfig(){
        try {
            initJdbcProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initJdbcProps() throws IOException{
        System.out.println("no jdbc properties file argument.\n use default path : " + propFilePath);
        propFile = new ClassPathResource(propFilePath).getFile();
        try (FileInputStream fis = new FileInputStream(propFile)){
            fis.read();
            jdbcProps.load(fis);
       }
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
    public BasicDataSource basicDataSource(Properties properties){
        BasicDataSource basicDataSource = new BasicDataSource();
        // basicDataSource.setUrl(url);
        // basicDataSource.setUsername(username);
        // basicDataSource.setPassword(password);
        // basicDataSource.setDriverClassName(driverClassName);
        // Method[] methods = basicDataSource.getClass().getMethod(name, parameterTypes) 
        return null;

    }

}
