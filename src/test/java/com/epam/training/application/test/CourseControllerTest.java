package com.epam.training.application.test;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import org.junit.Test;

import java.sql.Date;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest extends BasicTest {

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
        User student = new User();
        student.setEmail("test@mail.com");
        student.setId(1);
        int courseId=1;

        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(student);
        when(userServiceMock.removeCourseForUser(student.getId(),courseId)).thenReturn(1);

        try {
            mockMvc.perform(get("/deleteMyCourse/{id}",courseId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(redirectedUrl("/"));

            verify(userServiceMock,times(1)).getUserByEmail(student.getEmail());
            verify(userServiceMock,times(1)).removeCourseForUser(student.getId(),courseId);
            verifyNoMoreInteractions(userServiceMock);
            verifyZeroInteractions(courseServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void testAddCourseForStudent(){
        User student = new User();
        student.setId(1);
        student.setEmail("test@mail.com");

        Course course = new Course();
        course.setName("Course");
        course.setId(1);

        when(userServiceMock.getUserByEmail("test@mail.com")).thenReturn(student);
        when(userServiceMock.addCourse(student.getId(),course.getId())).thenReturn(course.getId());

        try {
            mockMvc.perform(get("/addCourse/{id}",course.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("forward:/"))
                    .andExpect(forwardedUrl("/"));

            verify(userServiceMock,times(1)).getUserByEmail(student.getEmail());
            verify(userServiceMock,times(1)).addCourse(student.getId(),course.getId());
            verifyNoMoreInteractions(userServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
