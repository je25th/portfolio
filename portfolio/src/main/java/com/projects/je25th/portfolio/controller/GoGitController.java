package com.projects.je25th.portfolio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.je25th.UfMm.dto.Log;
import com.projects.je25th.UfMm.service.LogService;

@Controller
//@RequestMapping({"/gogit", "/gogit1", })
public class GoGitController {
	
	@Autowired
	LogService logService;
	
	@GetMapping("gogit/{id}")
	public String gogit(@PathVariable(name="id")int id, HttpServletRequest request) {
		
		Log log = new Log();
		log.setIp(request.getHeader("x-real-ip") + "");
		if(id == 1)
			log.setMethod("saramin");
		else if(id == 2)
			log.setMethod("jobkorea");
		else
			log.setMethod("whoareyou");
		logService.writeLog(log);
		
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
		
		Log log = new Log();
		log.setIp(request.getHeader("x-real-ip") + "");
		log.setMethod("saramin(old)");
		logService.writeLog(log);
		
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
	
	@GetMapping("/gogit2")
	public String oldGogitJ(HttpServletRequest request) {
		
		Log log = new Log();
		log.setIp(request.getHeader("x-real-ip") + "");
		log.setMethod("jobkorea(old)");
		logService.writeLog(log);
		
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
}
