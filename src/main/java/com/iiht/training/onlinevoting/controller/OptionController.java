package com.iiht.training.onlinevoting.controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiht.training.onlinevoting.entity.PollOption;

@RequestMapping("/pollOption")
public class OptionController {

	@RequestMapping("/add/{id}")
	public String addOptionPage(@PathVariable("id") int id, Model model) {
		return "";
	}

	@RequestMapping("/handleForm")
	public String addOptionHandleForm(@Valid @ModelAttribute PollOption pollOption, @RequestParam("pollId") int id) {
		return "";
	}

	@RequestMapping("/toVote/{id}")
	public String displayOptionsToVoter(@PathVariable("id") int pollId, Model model) {
		return "";
	}
}
