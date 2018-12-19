package com.epam.training.application.test;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest extends BasicTest {

    private Course course;
    private User user;
    @Before
    @Override
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
         course = new Course("Test",new Date(1),new Date(2),new User());
         user = new User();
        user.setId(1);
        user.setEmail("test@mail.com");

    }
    @After
    public void tearDown(){
        user=null;
        course=null;
    }


    @Test
    public void testAddNewCourse(){
        Course course = new Course("Test",new Date(1),new Date(2),new User());
        when(courseServiceMock.saveOrUpdate(course)).thenReturn(1);
        when(userServiceMock.getById(0)).thenReturn(new User());

        try {
            mockMvc.perform(post("/addNewCourse")
                        .param("title",course.getName())
                        .param("startDate",course.getStartDate().toString())
                        .param("endDate",course.getEndDate().toString())
                        .param("teacher", course.getTeacher().toString())
                        .param("courseId"))
                    .andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/"))
                        .andExpect(redirectedUrl("/"));

            verify(courseServiceMock,times(1)).saveOrUpdate(course);
            verify(userServiceMock,times(1)).getById(0);
            verifyNoMoreInteractions(courseServiceMock,userServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteCourse(){
        int id=1;
        when(courseServiceMock.remove(id)).thenReturn(id);

        try {
            mockMvc.perform(get("/deleteCourse/{id}",id))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(redirectedUrl("/"));
            verify(courseServiceMock,times(1)).remove(id);
            verifyNoMoreInteractions(courseServiceMock);

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteCourseForStudent(){


        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(user);
        when(userServiceMock.removeCourseForUser(user.getId(),course.getId())).thenReturn(1);

        try {
            mockMvc.perform(get("/deleteMyCourse/{id}",course.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(redirectedUrl("/"));

            verify(userServiceMock,times(1)).getUserByEmail(user.getEmail());
            verify(userServiceMock,times(1)).removeCourseForUser(user.getId(),course.getId());
            verifyNoMoreInteractions(userServiceMock);
            verifyZeroInteractions(courseServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void testAddCourseForStudentSuccess(){

        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(user);
        when(userServiceMock.addCourse(user.getId(),course.getId())).thenReturn(course.getId());

        try {
            mockMvc.perform(get("/addCourse/{id}",course.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(forwardedUrl("/"));
            verify(userServiceMock,times(1)).getUserByEmail(user.getEmail());
            verify(userServiceMock,times(1)).addCourse(user.getId(),course.getId());
            verifyNoMoreInteractions(userServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
    @Test
    public void testAddCourseToStudentException(){
        user.setCourses(Arrays.asList(course));

        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(user);
        when(userServiceMock.addCourse(user.getId(),course.getId())).thenReturn(course.getId());
        try {
            mockMvc.perform(get("/addCourse/{id}",course.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().attribute("flashError","You already have this course!"))
                    .andExpect(view().name("redirect:/"))
                    .andExpect(forwardedUrl("/"));

            verify(userServiceMock,times(1)).getUserByEmail(user.getEmail());
            verify(userServiceMock,times(1)).addCourse(user.getId(),course.getId());
            verifyNoMoreInteractions(userServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
