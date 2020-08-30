package com.projects.je25th.portfolio.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

	HtmlEmail email = new HtmlEmail();
	
	public SendMailService() {
		//네이버 SMTP 이용
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("id", "password");
		
		//보안설정
		email.setSSL(true);
		email.setTLS(true);
	}
	
	public void sendMail(String subject, String contents) {
		try {
			//발신인 수신인 설정
			email.setFrom("id@naver.com", "방문 알람", "utf-8");
			email.addTo("id@gmail.com", "수신인", "utf-8");
			
			//제목
			email.setSubject(subject);
			
			//본문
			StringBuilder sb = new StringBuilder();
			sb.append("<htmil><body>");
			sb.append(contents);
			sb.append("</body></htmil>");
			
			//문자셋 설정
			email.setCharset("utf-8");
			email.setHtmlMsg(sb.toString());
			
			//전송
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	}
}
