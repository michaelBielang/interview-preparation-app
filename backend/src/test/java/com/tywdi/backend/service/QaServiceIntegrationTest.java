package com.tywdi.backend.service;

import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
@Transactional
@SpringBootTest
class QaServiceIntegrationTest {

    private static final String ANSWER = "integrationTestAnswer";
    private static final String QUESTION = "integrationTestQuestion";
    private static final QuestionAnswerDTO.Category CATEGORY = QuestionAnswerDTO.Category.BASIC;

    private final static String UPDATED_ANSWER = "updatedAnswer";
    private final static String UPDATED_QUESTION = "updatedQuestion";

    @Autowired
    private QaService qaService;

    //positive case
    @Test
    void addQa() {
        final Long id = qaService.addQa(ANSWER, QUESTION, CATEGORY).getId();
        assertThat(qaService.getQuestion(id.toString())).isNotNull();
    }

    //positive case
    @Test
    void updateQuestion() {
        final Long id = qaService.addQa(ANSWER, QUESTION, CATEGORY).getId();

        final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(UPDATED_ANSWER, UPDATED_QUESTION, CATEGORY);
        qaService.updateQuestion(questionAnswerDTO, id.toString());

        final Optional<QuestionAnswerDTO> updatedQA = qaService.getQuestion(id.toString());
        updatedQA.ifPresent(qa -> {
            assertThat(qa.getAnswer()).isEqualTo(UPDATED_ANSWER);
            assertThat(qa.getQuestion()).isEqualTo(UPDATED_QUESTION);
        });
    }

    //negative case
    @Test
    void updateQuestionWithException() {
        final QuestionAnswerDTO questionAnswerDTO = qaService.addQa(UPDATED_ANSWER, UPDATED_QUESTION, CATEGORY);
        assertThatThrownBy(() -> {
            qaService.updateQuestion(questionAnswerDTO, String.valueOf(questionAnswerDTO.getId() + 100));
        }).isInstanceOf(ResponseStatusException.class).hasMessageContaining("404 NOT_FOUND \"Element not present");
    }
}
