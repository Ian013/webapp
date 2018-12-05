package com.epam.training.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
            authenticationMgr
                    .jdbcAuthentication()
                    .dataSource(ds)
                    .usersByUsernameQuery(
                            "SELECT user.email,user.password,user.enabled " +
                            "from user WHERE email = ?"
            ).authoritiesByUsernameQuery("select user.email,role.name,user.enabled from user" +
                    " join user_has_role uhr on user.id = uhr.user_id " +
                    "join role on uhr.role_id = role.id where user.email=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                 .antMatchers("/courses")
                .access("hasRole('student')")

                .anyRequest()
                .permitAll()
                .and()

                .formLogin().loginPage("/loginPage")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/loginPage?logout").and()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .csrf();
    }


    private final DataSource ds;

    @Autowired
    public LoginSecurityConfig(DataSource ds) {
        this.ds = ds;
    }


    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
