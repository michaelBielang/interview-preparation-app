package com.tywdi.backend.service;

import com.tywdi.backend.model.qaVO.QuestionAnswerVO;
import com.tywdi.backend.repository.QaRepository;
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
 * Package: com.twentytwenty.qa.service
 * Date: 08.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ExtendWith(MockitoExtension.class)
class QaServiceTest {

    @Mock
    private QaRepository qaRepository;

    @InjectMocks
    private final QaService qaService = new QaService();

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private final ArgumentCaptor<QuestionAnswerVO> qaRepositoryArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswerVO.class);
    private final QuestionAnswerVO expectedObject = new QuestionAnswerVO(ANSWER, QUESTION);

    @Test
    void addQa() {
        qaService.addQa(ANSWER, QUESTION);

        verify(qaRepository, times(1)).save(qaRepositoryArgumentCaptor.capture());

        final QuestionAnswerVO capturedExpectedObject = qaRepositoryArgumentCaptor.getValue();

        assertThat(capturedExpectedObject.getAnswer()).isEqualTo(ANSWER);
        assertThat(capturedExpectedObject.getQuestion()).isEqualTo(QUESTION);
    }

    @Test
    void getQaList() {
        when(qaRepository.findAll()).thenReturn(List.of(expectedObject));

        final QuestionAnswerVO questionAnswerVO = qaService.getQaList().iterator().next();

        assertThat(questionAnswerVO).isEqualTo(expectedObject);
    }
}
