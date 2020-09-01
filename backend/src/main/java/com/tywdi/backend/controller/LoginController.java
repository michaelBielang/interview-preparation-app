package com.tywdi.backend.controller;

import com.tywdi.backend.model.DTO.AuthenticationRequest;
import com.tywdi.backend.model.DTO.JwtTokenResponse;
import com.tywdi.backend.model.User;
import com.tywdi.backend.service.AuthenticationService;
import com.tywdi.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
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
@Validated
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> createCustomer(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.generateJWTToken(request.getEmail(), request.getPassword()), HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // TODO - michael.bielang: 01.09.2020 follow up implementation for session based implementation
    @GetMapping
    public User getUser(@RequestParam("email") @NotBlank @Email final String email,
                        @RequestParam("password") @NotBlank @Min(5) final String password) {

        final User user = userService.getUser(email, password).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not registered");
        });

        final boolean passwordMatches = userService.verifyPassword(password, user.getPassword());

        if (passwordMatches) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        }
    }
}
