package com.tywdi.backend.controller;

import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.tywdi.backend.helper.JwtTokenHelper.withMailHeader;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 18.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class QaControllerIntegrationTest {

    private static final String GENERATED_EMAIL = RandomString.make(10);
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getQuestions() {
        final ResponseEntity<List<QuestionAnswerDTO>> listResponseEntity = testRestTemplate.exchange(
                "/questions", HttpMethod.GET, withMailHeader(GENERATED_EMAIL),
                new ParameterizedTypeReference<>() {
                });

        final List<QuestionAnswerDTO> questionAnswerDTOS = listResponseEntity.getBody();

        assertThat(listResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(questionAnswerDTOS.size()).isEqualTo(0);
    }
}
