package com.iiht.training.onlinevoting.functional;


import com.iiht.training.onlinevoting.Authentication.Authentication;
import com.iiht.training.onlinevoting.controller.AdminController;
import com.iiht.training.onlinevoting.dao.PollDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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


    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testLoginAdmin() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/admin/login")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("adminLogin") ? true : false, businessTestFile);

    }


}
