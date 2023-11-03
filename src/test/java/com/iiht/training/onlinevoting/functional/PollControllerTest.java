package com.iiht.training.onlinevoting.functional;

import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.controller.PollController;
import com.iiht.training.onlinevoting.dao.PollDAO;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollList;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PollControllerTest {

    @Mock
    private PollDAO pollDAO;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private PollController pollController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pollController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testAddPollsPageByAdmin() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(get("/poll/add")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("addPolls") ? true : false, businessTestFile);
    }

    @Test
    public void testAddPollsPageByOther() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(get("/poll/add")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);

    }

    @Test
    public void testAddPollHandleForm() throws Exception {
        Poll poll = getPoll();
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(post("/poll/handleForm")
                        .flashAttr("poll", poll))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/home") ? true : false, businessTestFile);
    }

    @Test
    public void testAddPollHandleFormAdmin() throws Exception {
        Poll poll = getPoll();
        List<Poll> polls = getPollList(5);
        when(pollDAO.getAll()).thenReturn(polls);
        MvcResult result = this.mockMvc.perform(post("/poll/handleForm")
                        .flashAttr("poll", poll))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/home") ? true : false, businessTestFile);
    }

    @Test
    public void testDeletePollByAdmin() throws Exception {
        Poll poll = getPoll();
        List<Poll> polls = getPollList(5);
        when(pollDAO.getAll()).thenReturn(polls);
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(get("/poll/deletePoll/" + poll.getPollId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/home") ? true : false, businessTestFile);
    }

    @Test
    public void testDeletePollByOther() throws Exception {
        Poll poll = getPoll();
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(get("/poll/deletePoll/" + poll.getPollId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);
    }

}
