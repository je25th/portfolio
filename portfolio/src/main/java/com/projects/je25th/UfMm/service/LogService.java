package com.projects.je25th.UfMm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.je25th.UfMm.dao.LogDao;
import com.projects.je25th.UfMm.dto.Log;

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
}
