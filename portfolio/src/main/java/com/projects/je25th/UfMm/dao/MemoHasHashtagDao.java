package com.projects.je25th.UfMm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projects.je25th.UfMm.dto.MemoHasHashtag;

@Repository
public class MemoHasHashtagDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MemoHasHashtag> rowMapper = BeanPropertyRowMapper.newInstance(MemoHasHashtag.class);
	
	public MemoHasHashtagDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<MemoHasHashtag> selectByHashtagIdx() {
		
		return null;
	}
	
	public void insert() {
		//TODO ::
	}
	
	public void update() {
		//TODO ::
	}
	
	public void deleteByMemoHasHashtagIdx() {
		//TODO ::
	}
	
	public void deleteByMemoIdx() {
		//TODO ::
	}
	
}
