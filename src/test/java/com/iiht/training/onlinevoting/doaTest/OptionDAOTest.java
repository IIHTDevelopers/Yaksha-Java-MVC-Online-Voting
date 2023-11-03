package com.iiht.training.onlinevoting.doaTest;

import com.iiht.training.onlinevoting.dao.OptionDAO;
import com.iiht.training.onlinevoting.entity.PollOption;
import com.iiht.training.onlinevoting.repository.PollOptionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOptionList;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class OptionDAOTest {

    @Mock
    private PollOptionRepository pollOptionRepository;

    @InjectMocks
    private OptionDAO optionDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testSaveOption() throws Exception {
        PollOption pollOption = getPollOption(1);
        boolean value = optionDAO.save(pollOption);
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testGetAllOption() throws Exception {
        List<PollOption> actual = getPollOptionList(1);
        when(pollOptionRepository.findAll()).thenReturn(actual);
        List<PollOption> expected = optionDAO.getAll();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetOptionById() throws Exception {
        PollOption actual = getPollOption(1);
        when(pollOptionRepository.findById(actual.getOptionId())).thenReturn(Optional.of(actual));
        PollOption expected = (PollOption) optionDAO.get(actual.getPollId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetOptionByPollId() throws Exception {
        List<PollOption> actual = getPollOptionList(1);
        when(pollOptionRepository.findByPollId(1)).thenReturn(actual);
        List<PollOption> expected = optionDAO.getOptionByPollId(1);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testDeleteOption() throws Exception {
        PollOption pollOption = getPollOption(1);
        boolean value = optionDAO.delete(pollOption.getPollId());
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

}
