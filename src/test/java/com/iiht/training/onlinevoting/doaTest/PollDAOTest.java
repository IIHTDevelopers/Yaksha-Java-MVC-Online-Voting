package com.iiht.training.onlinevoting.doaTest;

import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.repository.PollRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollList;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class PollDAOTest {

    @Mock
    private PollRepository pollRepository;

    @InjectMocks
    private PollDAO pollDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testSavePoll() throws Exception {
        Poll poll = getPoll();
        boolean value = pollDAO.save(poll);
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testGetPollById() throws Exception {
        Poll actual = getPoll();
        when(pollRepository.findById(actual.getPollId())).thenReturn(Optional.of(actual));
        Poll expected = (Poll) pollDAO.get(actual.getPollId().intValue());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetAllPoll() throws Exception {
        List<Poll> actual = getPollList(5);
        when(pollRepository.findAll()).thenReturn(actual);
        List<Poll> expected = pollDAO.getAll();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testDeletePoll() throws Exception {
        Poll poll = getPoll();
        boolean value = pollDAO.delete(poll.getPollId().intValue());
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testUpdatePoll() throws Exception {
        Poll poll = getPoll();
        boolean value = pollDAO.update(poll);
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

}
