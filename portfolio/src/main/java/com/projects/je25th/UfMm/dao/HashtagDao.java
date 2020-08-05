package com.projects.je25th.UfMm.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.projects.je25th.UfMm.dto.Hashtag;

@Repository
public class HashtagDao {
	
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
	private RowMapper<Hashtag> rowMapper = BeanPropertyRowMapper.newInstance(Hashtag.class);
	
	public HashtagDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("hashtag")
				.usingGeneratedKeyColumns("idx");
	}
	
	public List<Hashtag> selectByMemoIdx(int userIdx, int memoIdx) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("idx", memoIdx);
		List<Hashtag> results = jdbc.query("SELECT hashtag.idx, hashtag.user_idx, hashtag.hashtag, hashtag.count FROM memo " 
											+ "LEFT JOIN memo_has_hashtag ON memo.idx=memo_has_hashtag.memo_idx " 
											+ "INNER JOIN hashtag ON memo_has_hashtag.hashtag_idx=hashtag.idx " 
											+ "WHERE memo.idx = :idx AND memo.user_idx = :userIdx"
											, params
											, rowMapper);
		return results;
	}
	
	public List<Hashtag> selectByUserIdx(int userIdx) {
		Map<String, Object> params = Collections.singletonMap("userIdx", userIdx);
		List<Hashtag> results = jdbc.query("SELECT idx, user_idx, hashtag, count FROM hashtag " 
											+ "WHERE user_idx = :userIdx"
											, params
											, rowMapper);
		return results;
	}
	
	public Hashtag insert(Hashtag hashtag) {
		//TODO ::
		
		return hashtag;
	}
	
	public Hashtag update(Hashtag hashtag) {
		//TODO ::
		
		return hashtag;
	}
	
	public int deleteByHashtagIdx() {
		//TODO ::
		
		return 0;
	}
}
