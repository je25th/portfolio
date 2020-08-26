package com.projects.je25th.UfMm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.je25th.UfMm.service.MemoService;

@Controller
@RequestMapping("unfolded-memo/admin")
public class AdminController {

	@Autowired	
	MemoService memoService;
	
	@GetMapping("/reset/hashtag/count")
	public void resetHashtagCount(HttpServletResponse response) {
		memoService.allHashtagCountReset();
		
		PrintWriter out;
		try {
			out = response.getWriter();
			
			out.println("<HTML>");
			out.println("<head>");
			out.println("<title>Text</title>");
			out.println("</head>");
			out.println("<body>");

			out.println("success!");
			
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
