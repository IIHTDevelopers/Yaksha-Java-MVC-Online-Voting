package com.iiht.training.onlinevoting.entity;

public class VoteCount implements Models {

    private Long voteCountId;
    private int selectedOptionId;
    private int pollId;
    private int voterId;

    public Long getVoteCountId() {
        return voteCountId;
    }

    public void setVoteCountId(Long voteCountId) {
        this.voteCountId = voteCountId;
    }
    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }
}
