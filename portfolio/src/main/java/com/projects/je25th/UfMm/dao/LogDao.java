package com.projects.je25th.UfMm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.projects.je25th.UfMm.dto.Log;

@Repository
public class LogDao {

	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
	private RowMapper<Log> rowMapper = BeanPropertyRowMapper.newInstance(Log.class);
	
	public LogDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("log")
				.usingGeneratedKeyColumns("idx");
	}
	
	public List<Log> SelectAll() {
		List<Log> results = jdbc.query("SELECT * FROM log", rowMapper);
		
		return results;
	}
	
	public void insert(Log log) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(log);
		insertAction.execute(params);
	}
}
