package com.iiht.training.onlinevoting.repository;

import com.iiht.training.onlinevoting.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}
