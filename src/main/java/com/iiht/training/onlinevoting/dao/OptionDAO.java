package com.iiht.training.onlinevoting.dao;

import com.iiht.training.onlinevoting.entity.Models;
import com.iiht.training.onlinevoting.entity.PollOption;
import com.iiht.training.onlinevoting.repository.PollOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OptionDAO {
    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Models models) {
        PollOption pollOption = (PollOption) models;
        this.pollOptionRepository.save(pollOption);
    }
    public List<PollOption> getAll(){
        return this.pollOptionRepository.findAll();
    }

    public Object get(int id) {
        return this.pollOptionRepository.findById((long)id).get();
    }

    public List<PollOption> getOptionByPollId(int pollId) {
        String sql = "SELECT * FROM poll_option WHERE poll_Id = ?";
        return jdbcTemplate.query(sql, new Object[]{pollId}, new RowMapper<PollOption>() {
            @Override
            public PollOption mapRow(ResultSet rs, int rowNum) throws SQLException {
                PollOption pollOption = new PollOption();
                pollOption.setOptionId(rs.getLong(1));
                pollOption.setOptionName(rs.getString("optionName"));
                return pollOption;
            }
        });
    }


    public void delete(int id) {
        this.pollOptionRepository.deleteById((long)id);
    }

    public void update(int id, Models models) {
        //TODO
    }
}
