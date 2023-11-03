package com.iiht.training.onlinevoting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/voteCount")
public class VoteCountController {

	@RequestMapping("/addVote")
	public String addVote(@RequestParam("selectedOption") int selectedOptionId,
			@RequestParam("pollId") String pollIdString, HttpServletRequest request) {
		return "";
	}

	@RequestMapping("/result/{pollId}")
	public String result(@PathVariable("pollId") int pollId) {
		return "";
	}

}
