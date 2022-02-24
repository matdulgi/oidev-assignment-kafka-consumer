package org.oidev.assignment.kafka.consumer.config;

import com.dulgi.helper.annotation.NeedToChange;
import com.dulgi.helper.common.PropertiesParser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.query.spi.QueryPlanCache;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

//@org.springframework.context.annotation.Configuration
@NeedToChange("this Class can be added on JdbcConfiguration")
public class HibernateConfiguration {
    String configFilePath = "config/hibernate.properties";


    private enum CONFIGURATION_TYPE{
        XML, PROPERTIES
    }
    private enum MAPPING_TYPE{
        XML, ANNOTATION
    }

    private CONFIGURATION_TYPE configurationType = CONFIGURATION_TYPE.PROPERTIES;
    private MAPPING_TYPE mappingType = MAPPING_TYPE.ANNOTATION;


    SessionFactory sessionFactory;
    MetadataImplementor metadataImplementor;
    SessionFactoryOptions sessionFactoryOptions;
    QueryPlanCache.QueryPlanCreator queryPlanCreator;

    StandardServiceRegistry registry;



    /** select method to configuration
     * - Property-based
     * - XML-based
     *
     * Each method has to way for mapping Entity
     *  - XML-based
     *  - Annotation-based
     *
     */
    HibernateConfiguration (){
//        switch (configurationType) {
//            case XML:
//                xmlBasedConfiguration();
//                break;
//            case PROPERTIES:
//                propertyBasedConfiguration();
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + configurationType);
//        }
        propertyBasedConfiguration();
    }

    public void propertyBasedConfiguration(){
        Properties props = new PropertiesParser().parseProps(configFilePath);
        Configuration configuration = new Configuration().setProperties(props);
        annotationBasedMapping(configuration);
    }

    public void xmlBasedConfiguration(){
//        Configuration configuration = new Configuration().setProperties(props).addClass(Metric.class);
//        Configuration configuration = new Configuration().setProperties(props);
//        sessionFactory = configuration.buildSessionFactory();
//        System.out.println();
    }

    @NeedToChange("recursively")
    private void annotationBasedMapping(Configuration configuration){
        configuration.addAnnotatedClass(Metric.class);
        sessionFactory = configuration.buildSessionFactory();

    }


    @Bean
    public SessionFactory sessionFactory(){
        return sessionFactory;
    }
}
