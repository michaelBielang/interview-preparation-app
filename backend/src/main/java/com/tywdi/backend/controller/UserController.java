package com.tywdi.backend.controller;

import com.tywdi.backend.model.User;
import com.tywdi.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
public final class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add") //no vo just because :=)
    public void addUser(@RequestParam("username") @NotBlank final String username,
                        @RequestParam("password") @NotBlank @Min(5) final String password,
                        @RequestParam("email") @NotBlank @Email final String email) {
        userService.addUser(username, password, email);
    }

    @GetMapping
    public User getUser(@RequestParam("email") @NotBlank @Email final String email,
                        @RequestParam("password") @NotBlank @Min(5) final String password) {
        return userService.getUser(email, password);
    }

}
