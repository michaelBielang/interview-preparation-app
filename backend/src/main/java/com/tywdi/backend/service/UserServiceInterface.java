package com.tywdi.backend.service;

import com.tywdi.backend.model.User;
import com.tywdi.backend.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface UserServiceInterface extends UserDetailsService {
    Optional<User> getUser(String email, String password);

    Optional<User> updateUser(String username, String password, String email);

    @Transactional
    Optional<User> addUser(String username, String password, String email, Role role);

    String generatedEncodedPassword(String password);

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
