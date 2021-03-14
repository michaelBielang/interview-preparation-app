package com.tywdi.backend.controller;

import com.tywdi.backend.model.User;
import com.tywdi.backend.model.enums.Role;
import com.tywdi.backend.model.web.JwtTokenResponse;
import com.tywdi.backend.security.service.JwtTokenService;
import com.tywdi.backend.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Email;
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
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class UserController implements UserControllerInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtTokenResponse addUser(@RequestParam("username") @NotBlank final String username,
                                    @RequestParam("password") @Length(min = 5) final String password,
                                    @RequestParam("email") @Email final String email) {
        userService.addUser(username, password, email, Role.USER);

        return new JwtTokenResponse(jwtTokenService.generateToken(email));
    }


    @Override
    @PutMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestParam("password") @Length(min = 5) final String password,
                           @RequestParam("username") @NotBlank final String username,
                           @PathVariable("id") final String id) {
        return userService.updateUser(username, password, id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ""); // TODO - michael.bielang:
        });
    }
}
