package com.iiht.training.onlinevoting.repository;

import com.iiht.training.onlinevoting.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
    List<PollOption> findByPollId(int pollId);
}
