package com.iiht.training.onlinevoting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiht.training.onlinevoting.entity.Voter;

@RequestMapping("/voter")
public class VoterController {

	@RequestMapping("/handleLogin")
	public String handleLoginForm(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		return "";
	}

	@RequestMapping("/home")
	public String Home(Model model, HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/login")
	public String loginVoter() {
		return "";
	}

	@RequestMapping("/displayAll")
	public String displayVoters(Model model, HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/add")
	public String registerVotersPage(HttpServletRequest request) {
		return "";
	}

	@RequestMapping(value = "/handleForm", method = RequestMethod.POST)
	public String addVoterFormHandler(@Valid @ModelAttribute Voter voter, HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/deleteVoter/{id}")
	public String deleteVoter(@PathVariable("id") int id, HttpServletRequest request) {
		return "";
	}
}
