package com.iiht.training.onlinevoting.controller;

import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/poll")
public class PollController {
    @Autowired
    private PollDAO pollDAO;
    @Autowired
    private Authentication authentication;

    @RequestMapping("/add")
    public String addPollsPage(HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
           return "addPolls";
        }
        return "redirect:/admin/login";
    }
    @RequestMapping("/handleForm")
    public String addPollHandleForm(@ModelAttribute Poll poll) {
        poll.setStatus(true);
        poll.setWinner(null);
        pollDAO.save(poll);
        return "redirect:/admin/home";
    }
    @RequestMapping("/deletePoll/{id}")
    public String deletePoll(@PathVariable("id") int id, HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
            pollDAO.delete(id);
            return "redirect:/admin/home";
        }
        return "redirect:/admin/login";
    }

}
