package com.iiht.training.onlinevoting.doaTest;

import com.iiht.training.onlinevoting.dao.VoterDAO;
import com.iiht.training.onlinevoting.entity.Voter;
import com.iiht.training.onlinevoting.repository.VoterRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getVoter;
import static com.iiht.training.onlinevoting.testutils.MasterData.getVoterList;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class VoterDAOTest {

    @Mock
    private VoterRepository voterRepository;

    @InjectMocks
    private VoterDAO voterDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testSaveVoter() throws Exception {
        Voter voter = getVoter();
        boolean value = voterDAO.save(voter);
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testGetAllVoter() throws Exception {
        List<Voter> actual = getVoterList(5);
        when(voterRepository.findAll()).thenReturn(actual);
        List<Voter> expected = voterDAO.getAll();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetPollByIdForVoter() throws Exception {
        Voter actual = getVoter();
        when(voterRepository.findById(actual.getVoterId())).thenReturn(Optional.of(actual));
        Voter expected = (Voter) voterDAO.get(actual.getVoterId().intValue());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testDeleteVoterDAO() throws Exception {
        Voter actual = getVoter();
        boolean value = voterDAO.delete(actual.getVoterId().intValue());
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testGetVoterId() throws Exception {
        List<Voter> actual = getVoterList(5);
        Voter voter = getVoter();
        actual.add(voter);
        when(voterRepository.findAll()).thenReturn(actual);
        Long expected = voterDAO.getVoterId(voter.getVoterName(), voter.getVoterPassword());
        yakshaAssert(currentTest(),
                (Objects.equals(expected, voter.getVoterId())
                        ? "true"
                        : "false"),
                businessTestFile);
    }
}
