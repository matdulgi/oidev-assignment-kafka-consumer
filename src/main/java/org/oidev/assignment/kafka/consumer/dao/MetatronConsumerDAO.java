package org.oidev.assignment.kafka.consumer.dao;

import com.dulgi.helper.annotation.NeedToChange;
import com.dulgi.helper.sql.CreateSQL;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;

@Transactional
@Repository
public class MetatronConsumerDAO {

    JdbcTemplate jdbcTemplate;
    Set<String> tables;

    //tmp
    CreateSQL createSql = new CreateSQL("Metric");


    @Autowired
    public MetatronConsumerDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMetrics(Queue<Metric> dtoQueue){
        for(Metric metric : dtoQueue) {
            try {
                insertMetric(metric);
            } catch ( BadSqlGrammarException e ) {
//                if (e.getStackTrace()[0].toString()){
                e.printStackTrace();

                createTable(metric);
                insertMetric(metric);
//            }
//                else if
            }
        }
    }

    public void insertMetric(Metric metric){
        jdbcTemplate.queryForObject(genInsertMetricSQL(metric), new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        });
    }

    public void createTable(Metric metric){
        jdbcTemplate.query(genCreateMetricTableSQL(metric.getTableName(), ""), new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        });
    }

    @NeedToChange("It's temporary method to insert data")
    public String genInsertMetricSQL(Metric dto){
        return "insert into " + dto.getTableName() + " (system_seq, process_seq, metric_name, metric_value, type, timestamp) " +
                "values ( " +
                "'" + dto.getSystemSeq() + "' ," +
                "'" + dto.getProcessSeq() + "' ," +
                "'" + dto.getMetricName() + "' ," +
                "'" + dto.getMetricValue() + "' ," +
                "'" + dto.getType() + "' ," +
                "'" + dto.getTimestamp() + "' )";
    }

    @NeedToChange("It's temporary method to generate table")
    public String genCreateMetricTableSQL(String tableName, String metricType){
        String sql = "CREATE TABLE if not exists " + "oidev5." + tableName + " ( " +
                "'system_seq' VARCHAR(10) not null, " +
                "'process_seq' VARCHAR(10) not null, " +
                "'metric_name' VARCHAR(50) not null, " +
                "'metric_value' VARCHAR(100) not null, " +
                "'type' VARCHAR(100) not null, " +
                "'timestamp' VARCHAR(10) not null ," +
                "primary key ( system_seq, process_seq, metric_name ) " +
                ")" +
                "engine=InnoDB default charset=utf8" ;
        return sql;
    }





















//    jpa codes
//    @Autowired
//    public KafkaConsumerDAO(SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
//
//    public void insert(DTOEntity dtoEntity){
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.save(dtoEntity.getEntity());
//        session.getTransaction().commit();
//
//    }

}
