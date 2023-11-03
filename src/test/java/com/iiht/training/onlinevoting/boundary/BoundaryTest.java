package com.iiht.training.onlinevoting.boundary;

import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;
import com.iiht.training.onlinevoting.entity.Voter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.MasterData.getVoter;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testPollNameNotBlank() throws Exception {
        Poll poll = getPoll();
        poll.setPollName("");
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollNameNotNull() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(null);
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollNameMinThree() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(randomStringWithSize(2));
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollNameMaxFifty() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(randomStringWithSize(51));
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollOptionNameNotBlank() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName("");
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollOptionNameNotNull() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(null);
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollOptionNameMinThree() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(randomStringWithSize(2));
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPollOptionNameMaxFifty() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(randomStringWithSize(51));
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterNameNotBlank() throws Exception {
        Voter voter = getVoter();
        voter.setVoterName("");
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterNameNotNull() throws Exception {
        Voter voter = getVoter();
        voter.setVoterName(null);
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterNameMinThree() throws Exception {
        Voter voter = getVoter();
        voter.setVoterName(randomStringWithSize(2));
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterMaxFifty() throws Exception {
        Voter voter = getVoter();
        voter.setVoterName(randomStringWithSize(51));
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterPasswordNotBlank() throws Exception {
        Voter voter = getVoter();
        voter.setVoterPassword("");
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterPasswordNotNull() throws Exception {
        Voter voter = getVoter();
        voter.setVoterPassword(null);
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterPasswordMinThree() throws Exception {
        Voter voter = getVoter();
        voter.setVoterPassword(randomStringWithSize(2));
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testVoterPasswordMaxFifty() throws Exception {
        Voter voter = getVoter();
        voter.setVoterPassword(randomStringWithSize(51));
        Set<ConstraintViolation<Voter>> violations = validator.validate(voter);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
