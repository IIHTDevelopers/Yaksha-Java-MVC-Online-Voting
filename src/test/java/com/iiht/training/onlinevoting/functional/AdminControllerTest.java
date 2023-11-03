package com.iiht.training.onlinevoting.functional;

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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.controller.AdminController;
import com.iiht.training.onlinevoting.dao.PollDAO;
import com.iiht.training.onlinevoting.entity.Poll;

public class AdminControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PollDAO pollDAO;

	@Mock
	private Authentication authentication;

	@InjectMocks
	private AdminController adminController;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testLoginAdmin() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/admin/login")).andReturn();
		yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("adminLogin") ? true : false,
				businessTestFile);
	}

	@Test
    public void testAdminHome() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(10));
        MvcResult result = this.mockMvc.perform(post("/admin/home")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("adminLogin") ? true : false, businessTestFile);
    }

	@Test
	public void testAdminHome2() throws Exception {
		List<Poll> polls = getPollList(5);
		when(pollDAO.getAll()).thenReturn(polls);
		when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn("admin");
		MvcResult result = this.mockMvc.perform(post("/admin/home")).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView().getViewName().contentEquals("adminHomePage") ? true : false, businessTestFile);
	}

	@Test
    public void testFormHandler() throws Exception {
        when(authentication.authenticate(any(HttpServletRequest.class))).thenReturn(randomStringWithSize(10));
        MvcResult result = this.mockMvc.perform(post("/admin/handleForm")
                        .param("username", randomStringWithSize(10))
                        .param("password", randomStringWithSize(10)))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/admin/home") ? true : false, businessTestFile);
    }

	@Test
	public void testLogout() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/admin/logout")).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView().getViewName().contentEquals("redirect:/admin/login") ? true : false,
				businessTestFile);
	}

}
