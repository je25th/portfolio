package com.projects.je25th.portfolio.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//설정 파일임을 알림
public class DBConfig {

	private String driverClassName = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://je25th.cafe24.com/je25th";//"jdbc:mariadb://localhost:3306/je25th";

    private String username = "je25th"; 
    private String password = "awow35se!";
    
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		//커넥션풀
		dataSource.setInitialSize(2);//초기 커넥션 개수
		dataSource.setMaxActive(10);//최대 커넥션 개수
		
		return dataSource;
	}

}
