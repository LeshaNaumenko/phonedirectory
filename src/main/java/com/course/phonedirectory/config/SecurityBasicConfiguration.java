package com.course.phonedirectory.config;

import com.course.phonedirectory.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityBasicConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    public static void main(String[] args) {
        // spring 4.0.0
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        for (int i = 0; i < 5; i++) {
            // "123456" - plain text - user input from user interface
            String passwd = encoder.encode("1234");

            // passwd - password from database
            System.out.println(passwd); // print hash

            // true for all 5 iteration
            System.out.println(encoder.matches("1234", passwd));
            System.out.println(encoder.matches("1234", "$2a$10$PuYQZ2uQ7iknse0Zpn9t6OOyHUwcLseFsFz1H5xkUWoYfssBulYgy"));

        }
    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/personalinfo").hasAnyRole("REGISTERED_USER", "BOOKING_MANAGER")
                .antMatchers("/change/**").hasRole("BOOKING_MANAGER")
                .antMatchers("/getallusers").hasRole("BOOKING_MANAGER")
                .antMatchers("/").hasRole("BOOKING_MANAGER")
                .antMatchers("/save").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()

                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/")
//                .defaultSuccessUrl("/.html", true)
                .and()
                .logout().deleteCookies("JSESSIONID")

                .and()
                .rememberMe().key("uniqueAndSecret");
//                .defaultSuccessUrl("/personalinfo",true)
//                .loginProcessingUrl("/personalinfo");
    }

}
