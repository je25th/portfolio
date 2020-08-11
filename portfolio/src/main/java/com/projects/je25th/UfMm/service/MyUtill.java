package com.projects.je25th.UfMm.service;

import javax.servlet.http.HttpSession;

import com.projects.je25th.UfMm.dto.AuthInfo;

public class MyUtill {

	static public AuthInfo getAuthInfo(HttpSession session) {
			return (AuthInfo)session.getAttribute("authInfo");
	}
}
