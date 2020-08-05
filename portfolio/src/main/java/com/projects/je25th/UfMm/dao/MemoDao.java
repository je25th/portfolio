package com.projects.je25th.UfMm.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;

@Repository
public class MemoDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Memo> rowMapper = BeanPropertyRowMapper.newInstance(Memo.class);
	
	public MemoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//10개씩 끊어서 가져옴
	public List<Memo> selectByUserIdx(int userIdx, int page) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("page", (page-1)*10);
		List<Memo> results = jdbc.query("SELECT * FROM memo WHERE user_idx = :userIdx ORDER BY mdate DESC LIMIT :page, 10"
										, params
										, rowMapper);
		
		return results.isEmpty() ? null : results;
	}
	
	public Memo selectByMemoIdx(int userIdx, int memoIdx) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("memoIdx", memoIdx);
		List<Memo> results = jdbc.query("SELECT * FROM memo WHERE idx = :memoIdx AND user_idx = :userIdx"
										, params
										, rowMapper);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Memo> selectByHashtag(int userIdx, List<Hashtag> hashtaglist) {
		//TODO ::
		
		return null;
	}
	
	public List<Memo> selectByKeyword(int userIdx, String keyword) {
		//TODO ::
		//"SELECT * FROM memo WHERE user_idx = :userIdx AND (content LIKE '%:keyword%' OR title LIKE'%:keyword%') ORDER BY mdate DESC LIMIT :page , 10"
		return null;
	}
	
	public Memo insert(Memo memo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();//자동생성되는 키(memo.idx)값을 저장
		SqlParameterSource params = new BeanPropertySqlParameterSource(memo);//알아서 파라미터로 바꿔주나벼...
//		Map<String, Object> params = new HashMap<>();
//		params.put("userIdx", memo.getUserIdx());
//		params.put("title", memo.getTitle());
//		params.put("content", memo.getContent());
//		params.put("hashtag", memo.getHashtag());
//		params.put("colorbar", memo.getColorbar());
//		params.put("wowpoint", memo.getWowpoint());
//		params.put("fold", memo.getFold());
		jdbc.update("INSERT INTO memo (user_idx, title, content, hashtag, colorbar, wowpoint, fold) "
					+ "VALUES (:userIdx, :title, :content, :hashtag, :colorbar, :wowpoint, :fold)"
					, params, keyHolder);
		//자동생성된 키값 저장해줌
		memo.setIdx(keyHolder.getKey().intValue());
		
		return memo;
	}
	
	public Memo update(Memo memo) {
		//TODO ::
		
		return memo;
	}
	
	public int deleteByMemoIdx(int userIdx, int memoIdx) {
		Map<String, Integer> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("memoIdx", memoIdx);
		jdbc.update("DELETE FROM memo WHERE idx = :memoIdx AND userIdx = :userIdx", params);
		
		return 0;
	}
	
}