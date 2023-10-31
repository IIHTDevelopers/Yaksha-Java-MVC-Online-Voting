package com.iiht.training.onlinevoting.dao;

import com.iiht.training.onlinevoting.entity.Models;
import com.iiht.training.onlinevoting.entity.Voter;
import com.iiht.training.onlinevoting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VoterDAO {
    @Autowired
    private VoterRepository voterRepository;

    public void save(Models models) {
        Voter voter = (Voter) models;
        this.voterRepository.save(voter);
    }

    public List<Voter> getAll() {
        return this.voterRepository.findAll();
    }

    public Voter get(int id) {
        return (Voter) this.voterRepository.findById((long) id).get();
    }

    public void delete(int id) {
        this.voterRepository.deleteById((long) id);
    }

    public void update(int id, Models models) {
        Voter voter = (Voter) models;
//        TODO
    }

    public Long getVoterId(String nama, String passwrod) {
        List<Voter> voterList = getAll();
        for (Voter voter : voterList) {
            if (voter.getVoterName().equals(nama) && voter.getVoterPassword().equals(passwrod)) {
                return voter.getVoterId();
            }
        }
        return -1L;
    }
}
