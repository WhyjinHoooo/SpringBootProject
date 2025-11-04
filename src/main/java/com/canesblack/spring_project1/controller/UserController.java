package com.canesblack.spring_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.canesblack.spring_project1.entity.Role;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.UserService;

@Controller
public class UserController {
// 보안적으로 안정한 방법
//	private UserService userService;
//	
//	@Autowired
//	public UserController(UserService userService) {
//		this.userService = userService;
//	};
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user){
		String userPassword = user.getPassword();
		user.setRole(Role.MEMBER);
		
		String passwordEncoded = passwordEncoder.encode(userPassword);
		user.setPassword(passwordEncoded);
		userService.insertUser(user);
		
		return "redirect:/loginPage";
	}
	
	
}
