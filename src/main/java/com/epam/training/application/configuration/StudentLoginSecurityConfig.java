package com.epam.training.application.configuration;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
/*
@Configuration
@EnableWebSecurity
public class StudentLoginSecurityConfig extends WebSecurityConfigurerAdapter {

   /*
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        authenticationMgr.inMemoryAuthentication()
                .withUser("test_student")
                .password(encoder.encode("12345"))
                .authorities("ROLE_STUDENT");

    }
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/welcome").access("hasRole('ROLE_STUDENT')")
                .and()
                .formLogin().loginPage("/add-student")
                .defaultSuccessUrl("/login_sucess")
                .failureUrl("/error-page")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/welcome");
    }*/
/*

    private final
    DataSource ds;

    @Autowired
    public StudentLoginSecurityConfig(DataSource ds) {
        this.ds = ds;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

    String findUser = "SELECT lastName FROM student WHERE lastName =?";
    String findRoles = "SELECT lastName FROM student WHERE lastName =?";
        auth.jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery(findUser).authoritiesByUsernameQuery(findRoles);
    }
}
*/