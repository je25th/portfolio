package com.projects.je25th.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.projects.je25th.UfMm.dao.MemoDao;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.User;

@Configuration
@ComponentScan(basePackages = { "com.projects.je25th.UfMm.dao",  "com.projects.je25th.UfMm.service"}) //@Component, @Configuration, @Repository, @Service, @Controller, @RestController를 찾아서 자동으로 빈 등록해줌
@Import({ DBConfig.class })	
public class ApplicationConfig {
	
}
