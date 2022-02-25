package org.oidev.assignment.kafka.consumer.dao;

import org.oidev.assignment.kafka.consumer.dto.Metric;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }

    /**
     * 2.25 할것
     * JDBCTemplate 사용법 알알보기
     * Component("offersDao") 알아보기
     */
}
