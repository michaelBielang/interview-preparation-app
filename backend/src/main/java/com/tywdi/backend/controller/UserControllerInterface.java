package com.tywdi.backend.controller;

import com.tywdi.backend.model.DTO.JwtTokenResponse;
import com.tywdi.backend.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface UserControllerInterface {


    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    JwtTokenResponse addUser(@RequestParam("username") @NotBlank String username,
                             @RequestParam("password") @Length(min = 5) String password,
                             @RequestParam("email") @Email String email);

    @PutMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    User updateUser(@RequestParam("password") @Length(min = 5) String password,
                    @RequestParam("username") @NotBlank String username,
                    @PathVariable("id") String id);
}
