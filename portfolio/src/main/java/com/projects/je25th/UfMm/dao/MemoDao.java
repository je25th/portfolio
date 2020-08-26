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
	
	//10개씩 끊어서 가져옴
	public List<Memo> selectOnStarByUserIdx(int userIdx, int page) {
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("page", (page-1)*10);
		List<Memo> results = jdbc.query("SELECT * FROM memo WHERE user_idx = :userIdx AND star = 1 ORDER BY mdate DESC LIMIT :page, 10"
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
	
	public List<Memo> selectByHashtag(int userIdx, Hashtag hashtag) {
		//TODO ::
		
		return null;
	}
	
	public List<Memo> selectByKeyword(int userIdx, String keyword, int page) {
		//TODO ::
		//"SELECT * FROM memo WHERE user_idx = :userIdx AND (content LIKE '%:keyword%' OR title LIKE'%:keyword%') ORDER BY mdate DESC LIMIT :page , 10"
		Map<String, Object> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("keyword", "%" + keyword + "%");//LIKE는 이렇게 써야한다!!!!!
		params.put("page", (page-1)*10);
		List<Memo> results = jdbc.query("SELECT * FROM memo " 
											+ "WHERE user_idx = :userIdx AND (content LIKE :keyword OR title LIKE :keyword) ORDER BY mdate DESC LIMIT :page, 10"
											, params
											, rowMapper);
		return results;
	}
	
	public int insert(Memo memo) {
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
		jdbc.update("INSERT INTO memo (user_idx, title, content, hashtag, colorbar, wowpoint, fold, wdate, mdate) "
					+ "VALUES (:userIdx, :title, :content, :hashtag, :colorbar, :wowpoint, :fold, :wdate, :mdate)"
					, params, keyHolder);
		
		//자동생성된 키값 반환
		return keyHolder.getKey().intValue();
	}
	
	public void update(Memo memo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(memo);
		jdbc.update("UPDATE memo SET title=:title, content=:content, hashtag=:hashtag, colorbar=:colorbar, wowpoint=:wowpoint, fold=:fold, mdate=:mdate "
				+ "WHERE idx= :idx"
				, params);
		
	}
	
	public void updateStar(int memoIdx, int star) {
		Map<String, Integer> params = new HashMap<>();
		params.put("memoIdx", memoIdx);
		params.put("star", star);
		jdbc.update("UPDATE memo SET star=:star WHERE idx= :memoIdx"
				, params);
	}
	
	public int deleteByMemoIdx(int userIdx, int memoIdx) {
		Map<String, Integer> params = new HashMap<>();
		params.put("userIdx", userIdx);
		params.put("memoIdx", memoIdx);
		int result = jdbc.update("DELETE FROM memo WHERE idx = :memoIdx AND user_idx = :userIdx", params);
		
		return result;
	}
	
}
