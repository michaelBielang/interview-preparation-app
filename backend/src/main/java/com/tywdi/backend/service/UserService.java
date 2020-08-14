package com.tywdi.backend.service;

import com.tywdi.backend.model.User;
import com.tywdi.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 10.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final String email) {
        return Optional.ofNullable(userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not registered")));
    }

    public void updateUser(final User updatedUser, final String email) {
        final User user = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        // TODO: 14.08.2020
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(final User user) {
        userRepository.save(user);
    }

}
