package com.iiht.training.onlinevoting.repository;

import com.iiht.training.onlinevoting.entity.VoteCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteCountRepository extends JpaRepository<VoteCount, Long> {
}
