package com.iiht.training.onlinevoting.repository;

import com.iiht.training.onlinevoting.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
