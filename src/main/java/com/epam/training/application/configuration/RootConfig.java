package com.epam.training.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.training.application")
public class RootConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/view/","/WEB-INF/resources/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/WEB-INF/resources/javascript/");

    }
    @Bean
    public ViewResolver viewResolver() {
        {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/view/");
            resolver.setSuffix(".jsp");
            return resolver;
        }
    }


    /*@Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

       // exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();

        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }*/
}
