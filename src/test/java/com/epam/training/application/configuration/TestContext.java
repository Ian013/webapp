package com.epam.training.application.configuration;

import com.epam.training.application.service.ArchiveService;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.RoleService;
import com.epam.training.application.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public RoleService roleService(){return Mockito.mock(RoleService.class);}
    @Bean
    public UserService userService(){
        return Mockito.mock(UserService.class);
    }
    @Bean
    public CourseService courseService(){
        return Mockito.mock(CourseService.class);
    }
    @Bean
    public ArchiveService archiveService(){
        return Mockito.mock(ArchiveService.class);
    }

}
