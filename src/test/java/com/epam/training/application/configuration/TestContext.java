package com.epam.training.application.configuration;

import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    /*@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }*/
    @Bean
    public UserService userService(){
        return Mockito.mock(UserService.class);
    }
    @Bean
    public CourseService courseService(){
        return Mockito.mock(CourseService.class);
    }

}
