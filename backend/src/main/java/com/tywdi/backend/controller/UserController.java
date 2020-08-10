package com.tywdi.backend.controller;

import com.tywdi.backend.model.User;
import com.tywdi.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 10.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping(value = "/add")
    public void addUser(@RequestParam @NotBlank final String userName, @RequestParam @NotBlank final String password,
                        @RequestParam @NotBlank final String email) {
        final User user = new User(userName, email, password);

        userService.addUser(user);
    }

    @GetMapping
    public Optional<User> getUser(final String email) {
        return userService.getUser(email);
    }
}
