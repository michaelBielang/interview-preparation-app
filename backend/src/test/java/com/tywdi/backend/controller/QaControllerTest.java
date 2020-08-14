package com.tywdi.backend.controller;

import com.tywdi.backend.model.qaVO.QuestionAnswerDTO;
import com.tywdi.backend.service.QaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.controller
 * Date: 08.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ExtendWith(MockitoExtension.class)
class QaControllerTest {

    private static final String QUESTION = "testQuestion";
    private static final String ANSWER = "testAnswer";

    private final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(ANSWER, QUESTION);
    private final List<QuestionAnswerDTO> questionAnswerDTOList = List.of(questionAnswerDTO);
    private final ArgumentCaptor<QuestionAnswerDTO> questionAnswerVOArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswerDTO.class);

    @InjectMocks
    private final QaController qaController = new QaController();
    @Mock
    private QaService qaService;

    @Test
    void getQuestions() {
        when(qaService.getQaList()).thenReturn(questionAnswerDTOList);

        final QuestionAnswerDTO questionAnswerDTO = qaController.getQuestions().iterator().next();

        assertThat(questionAnswerDTO.getQuestion()).isEqualTo(QUESTION);
        assertThat(questionAnswerDTO.getAnswer()).isEqualTo(ANSWER);
    }

    @Test
    void addNewQa() {
        qaController.addNewQa(questionAnswerDTO);

        verify(qaService, times(1)).addQa(ANSWER, QUESTION);
    }
}
