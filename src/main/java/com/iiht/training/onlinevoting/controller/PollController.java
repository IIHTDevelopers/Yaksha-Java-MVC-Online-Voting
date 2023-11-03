package com.iiht.training.onlinevoting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iiht.training.onlinevoting.entity.Poll;

@RequestMapping("/poll")
public class PollController {

	@RequestMapping("/add")
	public String addPollsPage(HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/handleForm")
	public String addPollHandleForm(@Valid @ModelAttribute Poll poll) {
		return "";
	}

	@RequestMapping("/deletePoll/{id}")
	public String deletePoll(@PathVariable("id") int id, HttpServletRequest request) {
		return "";
	}

}
