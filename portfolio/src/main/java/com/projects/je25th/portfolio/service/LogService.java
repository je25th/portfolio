package com.projects.je25th.portfolio.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.je25th.portfolio.dao.LogDao;
import com.projects.je25th.portfolio.dto.Log;

@Service
public class LogService {

	@Autowired
	LogDao logDao;
	
	public List<Log> showLogList() {
		return logDao.SelectAll();
	}
	
	public void writeLog(Log log) {
		log.setRegdate(new Date());
		logDao.insert(log);
	}
	
	public int ipVisiteCount(String ip) {
		return logDao.selectIpCount(ip);
	}
}
