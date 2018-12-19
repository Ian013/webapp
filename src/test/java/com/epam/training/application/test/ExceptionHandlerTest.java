package com.epam.training.application.test;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExceptionHandlerTest extends BasicTest{

    @Test
    public void testSQLIntegrityConstraintViolationException(){
        Course course = new Course();
        course.setId(1);
        course.setName("Course");
        course.setStartDate(new Date(1000));
        course.setEndDate(new Date(2000));
        course.setTeacher(new User());

        User user = new User();
        user.setId(1);

        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(user);
        when(userServiceMock.addCourse(user.getId(),course.getId())).thenThrow(SQLIntegrityConstraintViolationException.class);

        try {
            mockMvc.perform(get("/addCourse/{id}",course.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().attribute("flashError","You already have this course!"))
                    .andExpect(view().name("redirect:/"))
                    .andExpect(forwardedUrl("/"));

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }

}
