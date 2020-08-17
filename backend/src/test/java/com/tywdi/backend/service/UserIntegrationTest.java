package com.tywdi.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 17.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserIntegrationTest {

    private final static String USER_NAME = "testUser";
    private final static String EMAIL = "email@test.de";
    private final static String PASSWORD = "123456";

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        userService.addUser(USER_NAME, PASSWORD, EMAIL);
        assertThat(userService.getUser(EMAIL, PASSWORD)).isNotNull();
    }

    @Test // negative
    public void addExistingUser() {
        userService.addUser("USER_NAME", "PASSWORD", EMAIL);
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> userService.addUser(USER_NAME, PASSWORD, EMAIL))
                .withMessageContaining("already registered");
    }
}
