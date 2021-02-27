package com.tywdi.backend.controller;

import com.tywdi.backend.model.User;
import com.tywdi.backend.model.dto.AuthenticationRequest;
import com.tywdi.backend.model.web.JwtTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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
public interface LoginControllerInterface {
    @PostMapping("/login")
    ResponseEntity<JwtTokenResponse> login(@RequestBody @Valid AuthenticationRequest request);

    // TODO - michael.bielang: 01.09.2020 follow up implementation for session based implementation
    @GetMapping
    User getUser(@RequestParam("email") @NotBlank @Email String email,
                 @RequestParam("password") @NotBlank @Min(5) String password);
}
