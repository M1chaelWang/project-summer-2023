package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.User;

public class UserController {
	
	public Object login(HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(username);
		User user = new User();
		user.setUserId("123456");
		user.setUserName("Michael");
		return user;
	}
}