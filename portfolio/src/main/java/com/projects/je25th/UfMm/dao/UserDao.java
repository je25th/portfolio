package com.projects.je25th.UfMm.dao;

import java.util.Collections;
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

import com.projects.je25th.UfMm.dto.User;

@Repository
public class UserDao {

	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;//쿼리 입력없이 지가 알아서 인서트 해주는 편리한것
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);//ResultSet에서 데이터를 읽어와
																					  //User 객체로 변환해주는 기능
	public UserDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);//주입
		this.insertAction = new SimpleJdbcInsert(dataSource)//주입
							.withTableName("user")//어느 테이블에 인서트 할것인지
							.usingGeneratedKeyColumns("idx");//테이블의 주키(pk)를 데이터 베이스가 자동 생성하고 있는 경우 호출하는 메서드이다
	}
	
	public User selectById(String id) {
		Map<String, Object> params = new HashMap<>();//파라미터(?기호에 들어갈 그거)
		params.put("id", id);
		List<User> results = jdbc.query("SELECT * FROM user WHERE id = :id", params, rowMapper);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public User selectByEmail(String email) {
		Map<String, Object> params = new HashMap<>();//파라미터(?기호에 들어갈 그거)
		params.put("email", email);
		List<User> results = jdbc.query("SELECT * FROM user WHERE email = :email", params, rowMapper);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public int deleteById(String id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		
		return jdbc.update("DELETE FROM guestbook WHERE id = :id", params);
	}
	
	public User Update(User user) {
		// TODO :: 회원정보 수정 쿼리(비밀번호, 닉네임, 이메일만 수정 가능)
		return user;
	}
	
	public User insert(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		user.setIdx(insertAction.executeAndReturnKey(params).intValue());
		
		return user;
	}
}
