package com.tywdi.backend.service;

import com.tywdi.backend.exceptions.UserExistsException;
import com.tywdi.backend.model.User;
import com.tywdi.backend.model.enums.Role;
import com.tywdi.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

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
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUser(final String email, final String password) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Optional<User> updateUser(final String username, final String password, final String email) {
        final User user = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return Optional.empty();
        // TODO: 17.08.2020
    }

    @Override
    @Transactional
    public Optional<User> addUser(final String username, final String password, final String email, final Role role) {

        if (userRepository.getUserByEmail(email).isPresent()) {
            throw new UserExistsException("user-is-already-registered", "User: " + username + " already in database");
        }

        final String encodedPW = generatedEncodedPassword(password);

        final User user = new User(username, email, encodedPW, role);

        return Optional.of(userRepository.save(user));
    }

    // TODO - michael.bielang: 01.09.2020 further implementation for a session based plattform
/*    @Transactional
    public Optional<User> addUser(final String username, final String password, final String email, final Role role) {

        if (userRepository.getUserByEmail(email).isPresent()) {
            return Optional.empty();
        }
        final String encodedPW = generatedEncodedPassword(password);

        final User user = new User(username, email, encodedPW, role);

        return Optional.of(userRepository.save(user));
    }*/

    @Override
    public String generatedEncodedPassword(final String password) {
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

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
