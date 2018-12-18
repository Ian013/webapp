package com.epam.training.application.test;

import com.epam.training.application.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SecurityControllerTest extends BasicTest {

    @Test
    public void testLoginPageViewName() throws Exception {
        mockMvc.perform(get("/loginPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andExpect(forwardedUrl("/WEB-INF/view/loginPage.jsp"));
    }
    @Test
    public void testRegisterPageModelAttribute(){
        try {
            mockMvc.perform(get("/registerPage"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("registerPage"))
                    .andExpect(forwardedUrl("/WEB-INF/view/registerPage.jsp"))
                    .andExpect(model().attributeExists("userForm"))
                    .andDo(print());

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
    @Test
    public void testRegisterSuccess(){
        User user = new User();
        user.setId(1);
        user.setFirstName("User");
        user.setLastName("User");
        user.setEmail("user@mail.com");
        user.setPassword("12345");

        when(userServiceMock.saveOrUpdate(user)).thenReturn(1);
        when(userServiceMock.getAll()).thenReturn(new ArrayList<>());
        when(roleServiceMock.setRoleForUser(user.getId(),1)).thenReturn(user.getId());
        try {
            mockMvc.perform(post("/register")
                        .param("email",user.getEmail())
                        .param("password",user.getPassword())
                        .param("lastName",user.getLastName())
                        .param("firstName",user.getFirstName()))
                    .andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/loginPage"));

            verify(userServiceMock,times(1)).saveOrUpdate(user);
            verify(userServiceMock,times(1)).getAll();
            verify(roleServiceMock,times(1));
            verify(userServiceMock.getUserByEmail(user.getEmail()),times(1));

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
    @Test
    public void testRegisterEmailAlreadyExist(){
        User user = new User();
        user.setId(1);
        user.setFirstName("User");
        user.setLastName("User");
        user.setEmail("user@mail.com");
        user.setPassword("12345");

        User secondUser = new User();
        secondUser.setId(2);
        user.setEmail("user@mail.com");

        when(userServiceMock.saveOrUpdate(user)).thenReturn(1);
        when(userServiceMock.getAll()).thenReturn(Arrays.asList(secondUser));
        try {
            mockMvc.perform(post("/register")
                    .param("email",user.getEmail())
                    .param("password",user.getPassword())
                    .param("lastName",user.getLastName())
                    .param("firstName",user.getFirstName()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("registerPage"))
                    .andExpect(model().attribute("error","Email already exists!"));

            verify(userServiceMock,times(1)).getAll();
            verifyZeroInteractions(roleServiceMock);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
