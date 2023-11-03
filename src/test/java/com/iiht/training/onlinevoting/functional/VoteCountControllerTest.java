package com.iiht.training.onlinevoting.functional;

import com.iiht.training.onlinevoting.controller.VoteCountController;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.dao.VoteCountDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomInteger;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class VoteCountControllerTest {

    @Mock
    private VoteCountDAO voteCountDAO;
    @Mock
    private PollDAO pollDAO;
    @InjectMocks
    private VoteCountController voteCountController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(voteCountController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testAddVote() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/voteCount/addVote")
                .param("selectedOption", randomInteger().toString())
                .param("pollId", randomInteger().toString())
                .sessionAttr("voterId", 1L)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/voter/home") ? true : false, businessTestFile);
    }

    @Test
    public void testResult() throws Exception {
        Poll poll = getPoll();
        String winner = randomStringWithSize(10);
        when(pollDAO.get(poll.getPollId().intValue())).thenReturn(poll);
        when(voteCountDAO.getWinner(poll.getPollId())).thenReturn(winner);
        MvcResult result = this.mockMvc.perform(post("/voteCount/result/" + poll.getPollId())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/home") ? true : false, businessTestFile);
    }

}
