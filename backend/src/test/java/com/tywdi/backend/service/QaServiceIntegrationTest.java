package com.tywdi.backend.service;

import com.tywdi.backend.exceptions.QuestionNotFoundException;
import com.tywdi.backend.model.dto.QuestionAnswerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    void getQa() {
        final QuestionAnswerDTO insertedQA = qaService.addQa(ANSWER, QUESTION, CATEGORY);

        final QuestionAnswerDTO questionAnswerFromService = qaService
                .getQuestion(String.valueOf(insertedQA.getId()));

        assertThat(questionAnswerFromService).isNotNull();
    }

    //positive case
    @Test
    void getQuestions() {
        qaService.addQa(ANSWER, QUESTION, CATEGORY);

        final List<QuestionAnswerDTO> answerDTOList = StreamSupport
                .stream(qaService.getQaList().spliterator(), false)
                .collect(Collectors.toList());

        assertThat(answerDTOList.size()).isEqualTo(1);
    }

    //positive case
    @Test
    void updateQuestion() {
        final Long id = qaService.addQa(ANSWER, QUESTION, CATEGORY).getId();

        final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(UPDATED_ANSWER, UPDATED_QUESTION, CATEGORY);
        qaService.updateQuestion(questionAnswerDTO, id.toString());

        final QuestionAnswerDTO updatedQA = qaService.getQuestion(id.toString());

        assertThat(updatedQA.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(updatedQA.getQuestion()).isEqualTo(UPDATED_QUESTION);
    }

    //negative case
    @Test
    void updateQuestionWithException() {
        final QuestionAnswerDTO questionAnswerDTO = qaService.addQa(UPDATED_ANSWER, UPDATED_QUESTION, CATEGORY);

        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() ->
                qaService.updateQuestion(questionAnswerDTO, String.valueOf(questionAnswerDTO.getId() + 100)))
                .withMessageContaining("not found");
    }
}
