package com.epam.training.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class RootConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/view/**")
                .addResourceLocations("/WEB-INF/view/");
        registry.addResourceHandler("/WEB-INF/view/resources/**")
                .addResourceLocations("/WEB-INF/view/resources/");

    }
}
