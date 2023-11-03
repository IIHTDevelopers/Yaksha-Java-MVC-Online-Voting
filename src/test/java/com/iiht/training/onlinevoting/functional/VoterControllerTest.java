package com.iiht.training.onlinevoting.functional;

import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.controller.VoterController;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.dao.VoteCountDAO;
import com.iiht.training.onlinevoting.dao.VoterDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.Voter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPollList;
import static com.iiht.training.onlinevoting.testutils.MasterData.getVoter;
import static com.iiht.training.onlinevoting.testutils.MasterData.getVoterList;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomBoolean;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class VoterControllerTest {

    @Mock
    private VoterDAO voterDAO;
    @Mock
    private PollDAO pollDao;
    @Mock
    private Authentication authentication;
    @Mock
    private VoteCountDAO voteCountDAO;
    @InjectMocks
    private VoterController voterController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(voterController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testLoginVoter() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/voter/login")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("voterLogin") ? true : false, businessTestFile);
    }

    @Test
    public void testHome() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(get("/voter/login")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("voterLogin") ? true : false, businessTestFile);
    }

    @Test
    public void testHome2() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("voter");
        List<Poll> polls = getPollList(5);
        when(pollDao.getAll()).thenReturn(polls);
        for (Poll poll : polls) {
            when(voteCountDAO.voted(poll.getPollId(), 1L)).thenReturn(randomBoolean());
        }
        MvcResult result = this.mockMvc.perform(get("/voter/home")
                .requestAttr("voterId", 1L)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("voterHome") ? true : false, businessTestFile);
    }

    @Test
    public void testHandleLoginForm() throws Exception {
        String username = randomStringWithSize(5);
        String password = randomStringWithSize(5);
        when(voterDAO.getVoterId(username, password)).thenReturn(1L);
        MvcResult result = this.mockMvc.perform(get("/voter/handleLogin")
                        .param("username", username)
                        .param("password", password))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/voter/home") ? true : false, businessTestFile);
    }

    @Test
    public void testDisplayVoters() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(get("/voter/displayAll")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);
    }

    @Test
    public void testDisplayVoters2() throws Exception {
        List<Voter> voters = getVoterList(5);
        when(voterDAO.getAll()).thenReturn(voters);
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(get("/voter/displayAll")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("displayVoters") ? true : false, businessTestFile);
    }

    @Test
    public void testRegisterVotersPage() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(get("/voter/add")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("addVoter") ? true : false, businessTestFile);
    }

    @Test
    public void testRegisterVotersPage2() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(get("/voter/add")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);
    }

    @Test
    public void testAddVoterFormHandler() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(post("/voter/handleForm")
                .flashAttr("voter", getVoter())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/voter/displayAll") ? true : false, businessTestFile);
    }

    @Test
    public void testAddVoterFormHandler2() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(post("/voter/handleForm")
                .flashAttr("voter", getVoter())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);
    }

    @Test
    public void testDeleteVoter() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
        MvcResult result = this.mockMvc.perform(post("/voter/deleteVoter/" + 1L)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/voter/displayAll") ? true : false, businessTestFile);
    }

    @Test
    public void testDeleteVoter2() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(5));
        MvcResult result = this.mockMvc.perform(post("/voter/deleteVoter/" + 1L)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false, businessTestFile);
    }
}
