package com.epam.training.application.test;


import com.epam.training.application.configuration.RootConfig;
import com.epam.training.application.configuration.TestContext;
import com.epam.training.application.domain.Course;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Qualifier("courseService")
    @Autowired
    private CourseService courseServiceMock;
    

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
    public void testIndexPageServicesAndView(){
        Course  java = new Course();
                java.setId(1);
                java.setName("Java");

        Course cSharp = new Course();
                cSharp.setId(2);
                cSharp.setName("C#");

        Course clojure = new Course();
                clojure.setId(3);
                clojure.setName("Clojure");

        Course cPlus = new Course();
                cPlus.setId(4);
                cPlus.setName("C++");

       when(courseServiceMock.getAll()).thenReturn(Arrays.asList(java,clojure,cPlus,cSharp));

        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"))
                    .andExpect(forwardedUrl("/WEB-INF/view/index.jsp"))
                    .andExpect(model().attribute("courses", hasItem(
                            allOf(
                                    hasProperty("id",is(1)),
                                    hasProperty("name",is("Java"))
                            ))))
                    .andExpect(model().attribute("courses",hasItem(
                            allOf(
                                    hasProperty("id",is(2)),
                                    hasProperty("name",is("C#"))
                            ))))
                    .andExpect(model().attribute("courses",hasItem(
                            allOf(
                                    hasProperty("id",is(3)),
                                    hasProperty("name",is("Clojure"))
                            ))))
                    .andExpect(model().attribute("courses",hasItem(
                            allOf(
                                    hasProperty("id",is(4)),
                                    hasProperty("name",is("C++"))
                            ))));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        verify(courseServiceMock,times(1)).getAll();
       verifyNoMoreInteractions(courseServiceMock);
    }
   @Test
    public void testIndexPage(){
        try {
            mockMvc.perform(get("/randomPage"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/loginPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loginPage"))
                .andExpect(forwardedUrl("/WEB-INF/view/loginPage.jsp"));
    }
}
