package org.oidev.assignment.kafka.consumer.dao;

import com.dulgi.helper.annotation.NeedToChange;
import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

@Transactional
@Repository
public class MetatronConsumerDAO {

    JdbcTemplate jdbcTemplate;


    //tmp
    //SQLs
//    CreateSQL createSql = new CreateSQL("Metric");


    @Autowired
    public MetatronConsumerDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public Set<String> searchTables(){
        // list is each row, map(column, value)
        List<Map<String, Object>> list = jdbcTemplate.queryForList("show tables");
        Set<String> set = new HashSet<String>();
        for(Map<String, Object> map : list){
            set.add((String)map.get(map.keySet().toArray()[0]));
        }
        return set;
    }

    public void insertMetrics(Queue<Metric> dtoQueue, Set<String> tableDict) {
        String tableName = null;
        try(Connection conn = jdbcTemplate.getDataSource().getConnection();
            Statement statement = conn.createStatement()){
            while(dtoQueue.peek()!= null) {
                tableName = dtoQueue.peek().getTableName();
                if (!tableDict.contains(tableName.toLowerCase())){
                    createTable(tableName);
                }
                statement.addBatch(genInsertMetricSQL(dtoQueue.poll()));
            }
            statement.executeBatch();
        } catch (SQLException e) {
//            e.printStackTrace();
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("Duplicate entry")){
                System.out.println("remove next queue");
//                dtoQueue.remove();
            }
            else if (Pattern.matches("Table.*doesn't exist",msg)){
                createTable(tableName);

            }
        }
    }

//    public void insertMetric(Metric[] metric){
//        jdbcTemplate.batchUpdate()
//    }

    public void createTable(String tableName){
        Metric metric = new Metric();
        metric.setTableName(tableName);
        createTable(metric);
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
    private String genInsertMetricSQL(Metric dto){
        return "insert into " + dto.getTableName() + " (system_seq, process_seq, metric_name, metric_value, type, timestamp) " +
                "values ( " +
                "'" + dto.getSystemSeq() + "' ," +
                "'" + dto.getProcessSeq() + "' ," +
                "'" + dto.getMetricName() + "' ," +
                "'" + dto.getMetricValue() + "' ," +
                "'" + dto.getType() + "' ," +
                "'" + dto.getTimestamp() + "' )";
    }

//    private String genInsertMetricBatchSql(List<Metric> metricList){
//        String sql = "insert into " + metricList.() + " (system_seq, process_seq, metric_name, metric_value, type, timestamp) " +
//            "values ( ?, ?, ?, ?, ?, ? )";
//        return "";
//    }


    @NeedToChange("It's temporary method to generate table")
    public String genCreateMetricTableSQL(String tableName, String metricType){
        String sql = "CREATE TABLE if not exists " + "oidev5." + tableName + " ( " +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "system_seq INT not null, " +
                "process_seq INT not null, " +
                "metric_name VARCHAR(50) not null, " +
                "metric_value VARCHAR(100) not null, " +
                "type VARCHAR(100) not null, " +
                "timestamp Timestamp not null " +
//                "primary key ( system_seq, process_seq, metric_name, timestamp) " +
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
