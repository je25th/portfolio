package com.projects.je25th.portfolio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projects.je25th.portfolio.dto.Log;
import com.projects.je25th.portfolio.service.LogService;
import com.projects.je25th.portfolio.service.SendMailService;

@Controller
public class GoGitController {
	
	@Autowired
	LogService logService;
	
	@Autowired
	SendMailService sendMailService;
	
	@GetMapping("gogit/{id}")
	public String gogit(@PathVariable(name="id")int id, HttpServletRequest request) {
		
		String ip = request.getHeader("x-real-ip") + "";
		String from = "whoareyou";
		
		Log log = new Log();
		log.setIp(ip);
		if(id == 1)
			from = "saramin";
		else if(id == 2)
			from = "jobkorea";
		log.setMethod(from);
		
		logService.writeLog(log);
		
		//이메일 보내기
		sendMail(ip, from, logService.ipVisiteCount(ip));
		
//		System.out.println(request.getRemoteAddr());
//		System.out.println(InetAddress.getLocalHost());
//		System.out.println(request.getRequestURI());
//		Enumeration<String> n = request.getHeaderNames();
//		while(n.hasMoreElements()) {
//			String name = n.nextElement();
//			System.out.println(name + ":" + request.getHeader(name));
//		}
		
		return "redirect:https://github.com/oij511/portfolio";
	}
	
	@GetMapping("/gogit1")
	public String oldGogitS(HttpServletRequest request) {
		
		String ip = request.getHeader("x-real-ip") + "";
		String from = "saramin(old)";
		
		Log log = new Log();
		log.setIp(ip);
		log.setMethod(from);
		logService.writeLog(log);
		
		//이메일 보내기
		sendMail(ip, from, logService.ipVisiteCount(ip));
		
		return "redirect:https://github.com/oij511/portfolio";
	}
	
	@GetMapping("/gogit2")
	public String oldGogitJ(HttpServletRequest request) {

		String ip = request.getHeader("x-real-ip") + "";
		String from = "jobkorea(old)";
		
		Log log = new Log();
		log.setIp(ip);
		log.setMethod(from);
		logService.writeLog(log);
		
		//이메일 보내기
		sendMail(ip, from, logService.ipVisiteCount(ip));
		
		return "redirect:https://github.com/oij511/portfolio";
	}
	
	@GetMapping("/checkkiraout")
	public void checkpage(HttpServletResponse response) {
		
		List<Log> resultList = logService.showLogList();
		
		PrintWriter out;
		try {
			out = response.getWriter();
			
			out.println("<HTML>");
			out.println("<head>");
			out.println("<title>Text</title>");
			out.println("</head>");
			out.println("<body>");
			
			for(int i=0; i<resultList.size(); i++) {
				out.println(resultList.get(i).toString() + "<br>");
			}
//			Enumeration<String> n = request.getHeaderNames();
//			while(n.hasMoreElements()) {
//				String name = n.nextElement();
//				out.println(name + ":" + request.getHeader(name));
//			}
			
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendMail(String ip, String from, int count) {
		sendMailService.sendMail("누군가가 내 포폴을 조회!"
				, "사이트 : " + from 
				+ "<br>방문자 : " + ip 
				+ "<br>방문횟수 : " + count 
				+ "<br>" + new Date().toString() 
				+ "<br>");
	}
}
