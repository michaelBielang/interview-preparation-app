package com.tywdi.backend.controller;

import com.arakelian.jackson.utils.JacksonUtils;
import com.tywdi.backend.model.DTO.AuthenticationRequest;
import com.tywdi.backend.model.DTO.JwtTokenResponse;
import com.tywdi.backend.security.JwtAuthenticationEntryPoint;
import com.tywdi.backend.security.JwtAuthenticationProvider;
import com.tywdi.backend.service.AuthenticationService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 06.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

// context version, heavy
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc

//light version
@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

    private static final String EMAIL = "test@mail.de";
    private static final String PASSWORD = "12345";
    private static final String API_URL = "/api";
    private static final String LOGIN_PATH = "/login";
    private static final String TOKEN = "123456";
    @MockBean
    JwtAuthenticationProvider JwtAuthenticationProvider;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationService authenticationService;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    void login() throws Exception {
        final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(TOKEN);
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(EMAIL, PASSWORD);

        when(authenticationService.generateJWTToken(EMAIL, PASSWORD)).thenReturn(jwtTokenResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(API_URL + LOGIN_PATH)
                .contextPath(API_URL)
                .content(JacksonUtils.toString(authenticationRequest, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value(TOKEN))
                .andExpect(status().isCreated());
    }

    @Test
    void validationError() throws Exception {
        final String shortPassword = RandomString.make(3);
        final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(TOKEN);
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(EMAIL, shortPassword);

        when(authenticationService.generateJWTToken(EMAIL, PASSWORD)).thenReturn(jwtTokenResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(API_URL + LOGIN_PATH)
                .contextPath(API_URL)
                .content(JacksonUtils.toString(authenticationRequest, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void notFound() throws Exception {
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(EMAIL, PASSWORD);

        when(authenticationService.generateJWTToken(EMAIL, PASSWORD)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.post(API_URL + LOGIN_PATH)
                .contextPath(API_URL)
                .content(JacksonUtils.toString(authenticationRequest, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
