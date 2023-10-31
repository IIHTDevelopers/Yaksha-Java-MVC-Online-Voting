package com.iiht.training.onlinevoting.controller;

import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.dao.VoteCountDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.VoteCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("voteCount")
public class VoteCountController {
    @Autowired
    private VoteCountDAO voteCountDAO;
    @Autowired
    private PollDAO pollDAO;


    @RequestMapping("/addVote")
    public String addVote(@RequestParam("selectedOption") int selectedOptionId, @RequestParam("pollId") String pollIdString, HttpServletRequest request) {
        VoteCount voteCount = new VoteCount();
        int pollId = Integer.parseInt(pollIdString.replace(",", ""));
        voteCount.setPollId(pollId);
        voteCount.setSelectedOptionId(selectedOptionId);
        int voterId = (int) request.getSession().getAttribute("voterId");
        voteCount.setVoterId(voterId);
        voteCountDAO.save(voteCount);
        return "redirect:/voter/home";
    }

    @RequestMapping("/result/{pollId}")
    public String result(@PathVariable("pollId") int pollId) {
        Poll poll = pollDAO.get(pollId);
        poll.setStatus(false);
        poll.setWinner(voteCountDAO.getWinner(poll.getPollId()));
        pollDAO.update(poll);
        return "redirect:/admin/home";
    }

}

