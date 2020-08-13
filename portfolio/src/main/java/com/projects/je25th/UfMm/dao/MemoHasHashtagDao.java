package com.projects.je25th.UfMm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.projects.je25th.UfMm.dto.MemoHasHashtag;

@Repository
public class MemoHasHashtagDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<MemoHasHashtag> rowMapper = BeanPropertyRowMapper.newInstance(MemoHasHashtag.class);
	
	public MemoHasHashtagDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("memo_has_hashtag")
				.usingGeneratedKeyColumns("idx");
	}
	
	public List<MemoHasHashtag> selectByMemoIdx(int userIdx, int memoIdx) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("memoIdx", memoIdx);
		List<MemoHasHashtag> results = jdbc.query("SELECT * FROM memo_has_hashtag "
												+ "WHERE user_idx = :userIdx AND memo_idx = :memoIdx"
												, params
												, rowMapper);
		
		return results;
	}
	
	public List<MemoHasHashtag> selectByHashtagIdx(int userIdx, int hashtagIdx) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("hashtagIdx", hashtagIdx);
		List<MemoHasHashtag> results = jdbc.query("SELECT * FROM memo_has_hashtag "
												+ "WHERE user_idx = :userIdx AND hashtag_idx = :hashtagIdx"
												, params
												, rowMapper);
		
		return results;
	}
	
	public int insert(MemoHasHashtag data) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public void update(MemoHasHashtag data) {
		//TODO ::
	}
	
	public int deleteByMemoHasHashtagIdx(int memoHasHashtagIdx) {
		Map<String, Integer> params = new HashMap<>();
		params.put("memoHasHashtagIdx", memoHasHashtagIdx);
		int result = jdbc.update("DELETE FROM memo_has_hashtag WHERE idx = :memoHasHashtagIdx", params);
		
		return result;
	}
	
	public int deleteByMemoIdx(int memoIdx) {
		Map<String, Integer> params = new HashMap<>();
		params.put("memoIdx", memoIdx);
		int result = jdbc.update("DELETE FROM memo_has_hashtag WHERE memo_idx = :memoIdx", params);
		
		return result;
	}
	
}
