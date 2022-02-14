package org.oidev.assignment.kafka.consumer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.dulgi.helper.common.CommonEntity;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JdbcConfiguration {
    Logger logger = LoggerFactory.getLogger(JdbcConfiguration.class);

    private Properties jdbcProps=null;
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
        System.out.println("jdbc properties file path : " + propFilePath);
        jdbcProps = new Properties();
        propFile = new ClassPathResource(propFilePath).getFile();
        try (FileInputStream fis = new FileInputStream(propFile)){
            fis.read();
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
    public BasicDataSource basicDataSource(@Qualifier("jdbcProps") Properties properties){
//        CommonEntity datsSourceEntity = new DataSourceEntity(BasicDataSource.class);
        CommonEntity<BasicDataSource> commonEntity = new CommonEntity<>(BasicDataSource.class);
        commonEntity.setEntityWithProp(properties);

        // basicDataSource.setUrl(url);
        // basicDataSource.setUsername(username);
        // basicDataSource.setPassword(password);
        // basicDataSource.setDriverClassName(driverClassName);
        BasicDataSource dataSource = commonEntity.getEntity();
        commonEntity.entityInfo();
        return dataSource;

    }

}
