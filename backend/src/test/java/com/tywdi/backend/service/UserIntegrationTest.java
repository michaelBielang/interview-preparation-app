package com.tywdi.backend.service;

import com.tywdi.backend.exceptions.UserExistsException;
import com.tywdi.backend.model.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        userService.addUser(USER_NAME, PASSWORD, EMAIL, Role.USER);
        assertThat(userService.getUser(EMAIL, PASSWORD)).isNotNull();
    }

    @Test // negative
    public void addExistingUser() {
        userService.addUser("USER_NAME", "PASSWORD", EMAIL, Role.USER);

        assertThatExceptionOfType(UserExistsException.class).isThrownBy(() ->
                userService.addUser(USER_NAME, PASSWORD, EMAIL, Role.USER));
    }
    // TODO - michael.bielang: 31.08.2020 more tests
}
