package com.tywdi.backend.controller;

import com.arakelian.jackson.utils.JacksonUtils;
import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import com.tywdi.backend.service.QaService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static com.tywdi.backend.helper.JwtTokenHelper.withMail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.controller
 * Date: 08.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class QaControllerTest {

    private static final String QUESTION = "testQuestion";
    private static final String ANSWER = "testAnswer";
    private static final QuestionAnswerDTO.Category CATEGORY = QuestionAnswerDTO.Category.BASIC;
    private static final String QA_URL = "/api";
    private static final String GENERATED_EMAIL = RandomString.make(10);

    private static final QuestionAnswerDTO QUESTION_ANSWER_DTO = new QuestionAnswerDTO(ANSWER, QUESTION, CATEGORY);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QaService qaService;

    @Test
    void getQuestion() throws Exception {
        final String id = "1";
        when(qaService.getQuestion(id)).thenReturn(Optional.of(QUESTION_ANSWER_DTO));

        mockMvc.perform(MockMvcRequestBuilders.get(QA_URL + "/question")
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.question").value(QUESTION))
                .andExpect(jsonPath("$.answer").value(ANSWER))
                .andExpect(jsonPath("$.category").value(CATEGORY.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void getQuestions() throws Exception {
        when(qaService.getQaList()).thenReturn(List.of(QUESTION_ANSWER_DTO));

        mockMvc.perform(MockMvcRequestBuilders.get(QA_URL + "/questions")
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].question").value(QUESTION))
                .andExpect(jsonPath("$[0].answer").value(ANSWER))
                .andExpect(jsonPath("$[0].category").value(CATEGORY.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void addNewQa() throws Exception {
        when(qaService.addQa(eq(ANSWER), eq(QUESTION), eq(CATEGORY))).thenReturn(QUESTION_ANSWER_DTO);

        mockMvc.perform(MockMvcRequestBuilders.post(QA_URL + "/question")
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .content(JacksonUtils.toString(QUESTION_ANSWER_DTO, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.question").value(QUESTION))
                .andExpect(jsonPath("$.answer").value(ANSWER))
                .andExpect(jsonPath("$.category").value(CATEGORY.toString()))
                .andExpect(status().isCreated());
    }

    @Test
    void updateQuestion() throws Exception {
        final String newAnswer = "newAnswer";
        final String newQuestion = "newQuestion";
        final String id = "1";

        final QuestionAnswerDTO updatedDTO = new QuestionAnswerDTO(newAnswer, newQuestion, CATEGORY);

        when(qaService.updateQuestion(QUESTION_ANSWER_DTO, id)).thenReturn(Optional.of(updatedDTO));

        mockMvc.perform(MockMvcRequestBuilders.put(QA_URL + "/question/" + 1)
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .content(JacksonUtils.toString(QUESTION_ANSWER_DTO, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.question").value(newQuestion))
                .andExpect(jsonPath("$.answer").value(newAnswer))
                .andExpect(jsonPath("$.category").value(CATEGORY.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void failUpdateQuestion() throws Exception {
        final String id = "1";
        when(qaService.updateQuestion(QUESTION_ANSWER_DTO, id)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put(QA_URL + "/question/{id}", id)
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .content(JacksonUtils.toString(QUESTION_ANSWER_DTO, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void failGetQuestion() throws Exception {
        final String id = "1";
        when(qaService.updateQuestion(QUESTION_ANSWER_DTO, id)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(QA_URL + "/question")
                .param("id", id)
                .headers(withMail(GENERATED_EMAIL))
                .contextPath(QA_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
