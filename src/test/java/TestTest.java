import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
public class TestTest {
    Logger logger = LoggerFactory.getLogger(TestTest.class);

    private Properties jdbcProps=null;
    private File propFile;
    private DataSource dataSource;
    private String propFilePath = "config/jdbc.propeties";

    public TestTest(){
        try {
            initJdbcProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initJdbcProps() throws IOException{
        System.out.println("no jdbc properties file argument.\n use default path : " + propFilePath);
        propFile = new ClassPathResource(propFilePath).getFile();
        try (FileInputStream fis = new FileInputStream(propFile)){
            fis.read();
            jdbcProps.load(fis);
       }
    }

    @Bean
    @Test
    //public void setJdbcProps(String path) throws IOException { }
    public DataSource datasource(){
        dataSource = new BasicDataSource();
        BasicDataSource basicDataSource = new BasicDataSource();
        //basicDataSource.set
        return dataSource;
    }
    

}
