package com.iiht.training.onlinevoting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/login")
	public String loginAdmin() {
		return "";
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		return "";
	}

	@RequestMapping("/handleForm")
	public String formHandler(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		return "";
	}
}
