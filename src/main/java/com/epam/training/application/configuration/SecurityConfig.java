package com.epam.training.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authenticationMgr) throws Exception {
            authenticationMgr
                    .jdbcAuthentication()
                    .dataSource(ds)
                    .usersByUsernameQuery(
                            "SELECT user.email,user.password,user.enabled " +
                            "FROM user WHERE email = ?"
            ).authoritiesByUsernameQuery(
                    "SELECT user.email,role.name,user.enabled FROM user " +
                    "JOIN user_has_role uhr on user.id = uhr.user_id " +
                    "JOIN role on uhr.role_id = role.id WHERE user.email=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/addCourse/*")
                    .access("hasAuthority('student')")
                .antMatchers("/delete/**","/addNewCourse/**","/deleteCourse/**")
                    .hasAuthority("admin")
                    .anyRequest()
                    .permitAll()
                .and()
                    .formLogin()
                    .loginPage("/loginPage")
                    .defaultSuccessUrl("/")
                    .failureUrl("/loginPage?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutSuccessUrl("/loginPage?logout")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                    .csrf().disable();
    }
    private final DataSource ds;

    @Autowired
    public SecurityConfig(DataSource ds) {
        this.ds = ds;
    }


    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
