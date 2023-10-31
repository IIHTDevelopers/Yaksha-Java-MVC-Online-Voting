package com.iiht.training.onlinevoting.repository;

import com.iiht.training.onlinevoting.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository  extends JpaRepository<Voter, Long> {
}
