package com.iiht.training.onlinevoting.Authentication;

import com.iiht.training.onlinevoting.entity.Voter;
import com.iiht.training.onlinevoting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class Authentication {
    @Autowired
    private VoterRepository voterRepository;

    public String authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String role = (String) session.getAttribute("role");

        if (username == null || password == null || role == null) {
            return "not authenticated";
        }
        if (role.equals("admin")) {
            if (username.equals("admin") && password.equals("admin")) {
                return "admin";
            }
        }
        if (role.equals("voter")) {
            List<Voter> voterList = voterRepository.findAll();
            for (Voter voter : voterList) {
                if (voter.getVoterName().equals(username) && voter.getVoterPassword().equals(password)) {
                    return "voter";
                }
            }
        }
        return "not authenticated";
    }
}
