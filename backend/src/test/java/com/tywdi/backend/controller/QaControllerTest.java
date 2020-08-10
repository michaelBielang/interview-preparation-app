package com.tywdi.backend.controller;

import com.tywdi.backend.qaVO.QuestionAnswerVO;
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

    private final QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO(QUESTION, ANSWER);
    private final List<QuestionAnswerVO> questionAnswerVOList = List.of(questionAnswerVO);
    private final ArgumentCaptor<QuestionAnswerVO> questionAnswerVOArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswerVO.class);

    @InjectMocks
    private final QaController qaController = new QaController();
    @Mock
    private QaService qaService;

    @Test
    void getQuestions() {
        when(qaService.getQaList()).thenReturn(questionAnswerVOList);

        final QuestionAnswerVO questionAnswerVO = qaController.getQuestions().iterator().next();

        assertThat(questionAnswerVO.getQuestion()).isEqualTo(QUESTION);
        assertThat(questionAnswerVO.getAnswer()).isEqualTo(ANSWER);
    }

    @Test
    void addNewQa() {
        qaController.addNewQa(questionAnswerVO);

        verify(qaService, times(1)).addQa(questionAnswerVOArgumentCaptor.capture());

        final QuestionAnswerVO capturedExpectedObject = questionAnswerVOArgumentCaptor.getValue();

        assertThat(capturedExpectedObject.getAnswer()).isEqualTo(ANSWER);
        assertThat(capturedExpectedObject.getQuestion()).isEqualTo(QUESTION);
    }
}
