package com.epam.training.application.test;


import org.apache.log4j.Logger;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserControllerTest extends BasicTest{

   private final static Logger LOG = Logger.getLogger(UserControllerTest.class);
    @Test
    public void testRemoveStudent(){
        when(userServiceMock.remove(1))
                .thenReturn(1);
        try {
            mockMvc.perform(get("/users/deleteStudent/{id}",1))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(redirectedUrl("/"));
            verify(userServiceMock,times(1)).remove(1);

        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Test
    public void testDeleteCourseForStudent(){
        when(userServiceMock.removeCourseForUser(1,1))
                .thenReturn(1);
        try {
            mockMvc.perform(get("/deleteStudentFromCourse/{courseId}+{studentId}",1,1))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"));
            verify(userServiceMock,times(1)).removeCourseForUser(1,1);

        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Test
    public void testMarkStudent(){
        when(archiveServiceMock.setMarkForStudent(1,1,10))
                .thenReturn(10);
        try {
            mockMvc.perform(post("/markStudent")
                        .param("mark","10")
                        .param("courseId","1")
                        .param("studentId","1"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"));
            verify(archiveServiceMock,times(1))
                    .setMarkForStudent(1,1,10);
         } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
