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
    private String url = "jdbc:mariadb://localhost:3306/je25th";//je25th.cafe24.com//localhost:3306

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
		dataSource.setTestWhileIdle(true);//유휴 커넥션 검사
		dataSource.setMinEvictableIdleTimeMillis(1000 * 60 * 3);//커넥션 풀에 유휴 상태로 유지할 최소 시간 : 3분
																//유휴시간이 초과한 커넥션은 풀에서 제거함(setTestWhileIdle이 true일 경우)
		dataSource.setTimeBetweenEvictionRunsMillis(1000 * 10);//커넥션 풀의 유휴 커넥션을 검사할 주기 지정 : 10초
		
		return dataSource;
	}
	
	//트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
