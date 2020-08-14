package com.tywdi.backend.service;

import com.tywdi.backend.model.User;
import com.tywdi.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(final String email, final String password) {
        final User user = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not registered"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not registered");
        }
        return user;
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
    public void addUser(final String username, final String password, final String email) {
        if (userRepository.getUserByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + email + " already registered");
        }
        final String encodedPW = generatedEncodedPassword(password);

        final User user = new User(username, email, encodedPW);

        userRepository.save(user);
    }

    private String generatedEncodedPassword(final String password) {
        String hashedPassword = passwordEncoder.encode(password);
        int counter = 0;
        while (passwordEncoder.upgradeEncoding(hashedPassword) && counter < 5) {
            hashedPassword = passwordEncoder.encode(password);
            counter++;
        }
        if (counter == 5) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again later");
        }
        return hashedPassword;
    }
}
