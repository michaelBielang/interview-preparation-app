package com.tywdi.backend.service;

import com.tywdi.backend.model.web.JwtTokenResponse;
import com.tywdi.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class AuthenticationService implements AuthenticationServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public JwtTokenResponse generateJWTToken(final String email, final String password) {
        return userRepository.getUserByEmail(email)
                .filter(account -> passwordEncoder.matches(password, account.getPassword()))
                .map(account -> new JwtTokenResponse(jwtTokenService.generateToken(email)))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }
}
