package com.iiht.training.onlinevoting.controller;

import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.dao.VoteCountDAO;
import com.iiht.training.onlinevoting.dao.VoterDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/voter")
public class VoterController {
    @Autowired
    private VoterDAO voterDAO;
    @Autowired
    private PollDAO pollDao;
    @Autowired
    private Authentication authentication;
    @Autowired
    VoteCountDAO voteCountDAO;

    @RequestMapping("/handleLogin")
    public String handleLoginForm(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        session.setAttribute("role","voter");
        session.setAttribute("voterId",voterDAO.getVoterId(username,password));
        return "redirect:/voter/home";
    }
    @RequestMapping("/home")
    public String Home(Model model,HttpServletRequest request) {
        if(authentication.authenticate(request).equals("voter")){
            List<Poll> polls = pollDao.getAll();
            for(Poll poll:polls){
                poll.setVoted(voteCountDAO.voted(poll.getPollId(),(Long) request.getSession().getAttribute("voterId")));
            }
            model.addAttribute("polls",polls);
            return "voterHome";
        }
        return "redirect:/voter/login";
    }

    @RequestMapping("/login")
    public String loginVoter() {
        return "voterLogin";
    }

    @RequestMapping("/displayAll")
    public String displayVoters(Model model, HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
            List<Voter> voters = voterDAO.getAll();
            model.addAttribute("voters", voters);
            return "displayVoters";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/add")
    public String registerVotersPage(HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
            return "addVoter";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/handleForm",method = RequestMethod.POST)
    public String addVoterFormHandler(@ModelAttribute Voter voter, HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
            voterDAO.save(voter);
            return "redirect:displayAll";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/deleteVoter/{id}")
    public String deleteVoter(@PathVariable("id") int id, HttpServletRequest request) {
        if(authentication.authenticate(request).equals("admin")){
            voterDAO.delete(id);
            return "redirect:/voter/displayAll";
        }
        return "redirect:/admin/login";
    }
}
