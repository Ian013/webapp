package com.epam.training.application.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.training.application.configuration.RootConfig;
import com.epam.training.application.configuration.TestContext;

import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestContext.class, RootConfig.class})
public class ControllerTest {

    private final static Logger LOG = Logger.getLogger(ControllerTest.class);

    @Autowired
    private WebApplicationContext wac;


    @Qualifier("userService")
    @Autowired
    private UserService userServiceMock;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void testIndexPageViewName(){
        try{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
        } catch (Exception e) {
           LOG.error(e.getMessage());
        }

    }
   @Test
    public void testIndexPage(){
        try {
            mockMvc.perform(get("/43"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userServiceMock.getById(1);
    }
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/loginPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loginPage"));
    }

}
