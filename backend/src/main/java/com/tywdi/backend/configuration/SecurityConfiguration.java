package com.tywdi.backend.configuration;

import com.tywdi.backend.exceptionhandler.FilterChainExceptionHandler;
import com.tywdi.backend.security.JwtAuthenticationEntryPoint;
import com.tywdi.backend.security.JwtAuthenticationProvider;
import com.tywdi.backend.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

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

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private FilterChainExceptionHandler filterChainExceptionHandler;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationEntryPoint unauthorizedHandler,
                                 JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
/*
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
*/

        httpSecurity
                .csrf().disable()
                .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }
}
