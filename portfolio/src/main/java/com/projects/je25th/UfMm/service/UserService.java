package com.projects.je25th.UfMm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.projects.je25th.UfMm.dao.UserDao;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.User;

@Service
public class UserService {

	@Autowired//자동 의존 주입
	UserDao userDao;
	
	@Transactional(readOnly=true)//해당 트랜잭션을 읽기 전용모드로 처리
	public void logout() {
		//TODO :: 로그아웃 로직
	}

	@Transactional(readOnly=true)//해당 트랜잭션을 읽기 전용모드로 처리
	public User login(String id, String pw) {
		User check = userDao.selectById(id);
		
		if(check != null && check.getPw().equals(pw) ) 
			return check;
		
		return null;
	}
	
	@Transactional(readOnly=false)
	public int deleteUser(String id) {
		return userDao.deleteById(id);
	}
	
	@Transactional(rollbackFor={Exception.class})
	public boolean addUser(User user) {
		boolean result = true;
		
		try {
			userDao.insert(user);
		} catch(Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		
		return result;
	}
	
	@Transactional(readOnly=true)
	public boolean checkEnableId(String id) {
		//같은 아이디가 있을 경우 false 리턴
		return userDao.selectById(id)==null ? true : false;
	}
	
	@Transactional(readOnly=true)
	public boolean checkEnableEmail(String email) {
		//같은 이메일이 있을 경우 false 리턴
		return userDao.selectByEmail(email)==null ? true : false;
	}
}
