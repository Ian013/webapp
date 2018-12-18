package com.epam.training.application.test;

import com.epam.training.application.configuration.TestContext;
import com.epam.training.application.configuration.WebConfiguration;
import com.epam.training.application.service.ArchiveService;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.RoleService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SuppressWarnings("WeakerAccess")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestContext.class, WebConfiguration.class})
public abstract class BasicTest {

    protected final static Logger LOG = Logger.getLogger(UserControllerTest.class);

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }



    @Qualifier("userService")
    @Autowired
    protected UserService userServiceMock;

    @Qualifier("courseService")
    @Autowired
    protected CourseService courseServiceMock;

    @Qualifier("archiveService")
    @Autowired
    protected ArchiveService archiveServiceMock;

    @Qualifier("roleService")
    @Autowired
    protected RoleService roleServiceMock;

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;
}
