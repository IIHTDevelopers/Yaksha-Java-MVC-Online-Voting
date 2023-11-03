package com.iiht.training.onlinevoting.functional;

import com.iiht.training.onlinevoting.controller.OptionController;
import com.iiht.training.onlinevoting.dao.OptionDAO;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOptionList;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class OptionControllerTest {

    @Mock
    private OptionDAO optionDAO;

    @Mock
    private PollDAO pollDAO;

    @InjectMocks
    private OptionController optionController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(optionController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testAddOptionPage() throws Exception {
        Poll poll = getPoll();
        when(pollDAO.get((poll.getPollId().intValue()))).thenReturn(poll);
        List<PollOption> pollOptions = getPollOptionList(poll.getPollId().intValue());
        when(optionDAO.getOptionByPollId(poll.getPollId().intValue())).thenReturn(pollOptions);
        MvcResult result = this.mockMvc.perform(get("/pollOption/add/" + poll.getPollId())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("addOption") ? true : false, businessTestFile);
    }

    @Test
    public void testAddOptionHandleForm() throws Exception {
        Poll poll = getPoll();
        PollOption pollOption = getPollOption(poll.getPollId().intValue());
        when(pollDAO.get((poll.getPollId().intValue()))).thenReturn(poll);
        List<PollOption> pollOptions = getPollOptionList(poll.getPollId().intValue());
        when(optionDAO.getOptionByPollId(poll.getPollId().intValue())).thenReturn(pollOptions);

        MvcResult result = this.mockMvc.perform(post("/pollOption/handleForm")
                        .param("pollId", poll.getPollId().toString())
                        .flashAttr("pollOption", pollOption))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:add/" + poll.getPollId()) ? true : false, businessTestFile);
    }

    @Test
    public void testDisplayOptionsToVoter() throws Exception {
        Poll poll = getPoll();
        when(pollDAO.get((poll.getPollId().intValue()))).thenReturn(poll);
        List<PollOption> pollOptions = getPollOptionList(poll.getPollId().intValue());
        when(optionDAO.getOptionByPollId(poll.getPollId().intValue())).thenReturn(pollOptions);
        MvcResult result = this.mockMvc.perform(post("/pollOption/toVote/" + poll.getPollId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("optionsToVote") ? true : false, businessTestFile);
    }
}
