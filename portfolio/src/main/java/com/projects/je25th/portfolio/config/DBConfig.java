package com.projects.je25th.portfolio.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration//설정 파일임을 알림
@EnableTransactionManagement//트랜잭셔널 애노테이션이 붙은 메서드를 트랜잭션 범위에서 실행하는 기능 활성화
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
	
	//트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
