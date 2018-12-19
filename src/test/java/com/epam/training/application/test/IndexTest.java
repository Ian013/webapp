package com.epam.training.application.test;

import com.epam.training.application.domain.Course;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexTest extends BasicTest {
    @Test
    public void testIndexPageViewName(){
        try{
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"))
                    .andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }

    @Test
    public void test404(){
        try{
            mockMvc.perform(get("/randomPage"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void testIndexPageParams(){
        Course java = new Course();
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
}
