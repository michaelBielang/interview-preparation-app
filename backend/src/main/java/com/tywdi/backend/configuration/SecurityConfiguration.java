package com.tywdi.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Michael Bielang on 19.07.2018.
 * www.codemerger.com
 * bielang@codemerger.com
 * <p>
 * Project: www.tradeMerger.com
 * java version "1.8.0_131"
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
        // TODO - michael.bielang: implement security
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable Cross Site Request Forgery
        http.csrf().disable(); // TODO: 15.08.2020

        // The pages does not require login
        http.authorizeRequests() // TODO: 15.08.2020 deactivate
                .anyRequest()
                .permitAll();

        //later
        /*http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true);*/
    }
}
