package com.iiht.training.onlinevoting.dao;

import java.util.List;

import com.iiht.training.onlinevoting.entity.Models;
import com.iiht.training.onlinevoting.entity.VoteCount;

public class VoteCountDAO {

	public void save(Models models) {
	}

	public String getWinner(Long pollId) {
		return "";
	}

	public List<VoteCount> getAll() {
		return null;
	}

	private int findMostFrequent(List<Integer> list) {
		return 0;
	}

	public boolean voted(Long pollId, Long voterId) {
		return false;
	}
}
