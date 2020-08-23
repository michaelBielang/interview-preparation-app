package com.tywdi.backend.controller;

import com.arakelian.jackson.utils.JacksonUtils;
import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import com.tywdi.backend.service.QaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 18.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class QaControllerIntegrationTest {

    private static final String QA_URL = "/api";
    private static final String QUESTION = "testQuestion";
    private static final String ANSWER = "testAnswer";
    private static final QuestionAnswerDTO.Category CATEGORY = QuestionAnswerDTO.Category.BASIC;

    private final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(ANSWER, QUESTION, CATEGORY);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QaService qaService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setupMocks() {
        final List<QuestionAnswerDTO> questionAnswerDTOList = List.of(questionAnswerDTO);
        when(qaService.getQaList()).thenReturn(questionAnswerDTOList);
        when(qaService.addQa(ANSWER, QUESTION, CATEGORY)).thenReturn(questionAnswerDTO);
    }

    @Test
    void getQuestions() {
        final ResponseEntity<List<QuestionAnswerDTO>> listResponseEntity = testRestTemplate.exchange(
                "/questions", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertThat(listResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(listResponseEntity.getBody().get(0).getQuestion()).isEqualTo(QUESTION);
    }

    @Test
    void addNewQa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(QA_URL + "/question")
                .contextPath(QA_URL)
                .content(JacksonUtils.toString(questionAnswerDTO, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getQuestion() {
        // TODO - michael.bielang:
    }


    @Test
    void updateQuestion() {
        // TODO - michael.bielang:
    }
}
