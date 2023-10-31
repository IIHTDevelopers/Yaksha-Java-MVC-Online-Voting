package com.iiht.training.onlinevoting.dao;

import com.iiht.training.onlinevoting.entity.Models;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
public class PollDAO{
    @Autowired
    private PollRepository pollRepository;

    @Transactional
    public void save(Models models) {
        Poll poll = (Poll) models;
        this.pollRepository.save(poll);
    }

    public Poll get(int id) {
        return (Poll) this.pollRepository.findById((long)id).get();
    }

    public List<Poll> getAll() {
        return this.pollRepository.findAll();
    }

    @Transactional
    public void delete(int id) {
        this.pollRepository.deleteById((long)id);
    }
    @Transactional
    public void update(Models models) {
        Poll poll = (Poll) models;
        this.pollRepository.save(poll);
    }

}
