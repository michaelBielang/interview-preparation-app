package com.tywdi.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.configuration
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface SecurityConfigurationInt {

    @Bean
    PasswordEncoder passwordEncoder();

    @Autowired
    void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder);

    void configure(HttpSecurity httpSecurity) throws Exception;
}
