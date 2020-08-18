package com.tywdi.backend.service;

import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
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

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final QuestionAnswerDTO.Category CATEGORY = QuestionAnswerDTO.Category.BASIC;
    @InjectMocks
    private final QaService qaService = new QaService();
    private final ArgumentCaptor<QuestionAnswerDTO> qaRepositoryArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswerDTO.class);
    private final QuestionAnswerDTO expectedObject = new QuestionAnswerDTO(ANSWER, QUESTION, CATEGORY);
    @Mock
    private QaRepository qaRepository;

    @Test
    void addQa() {
        qaService.addQa(ANSWER, QUESTION, CATEGORY);

        verify(qaRepository, times(1)).save(qaRepositoryArgumentCaptor.capture());

        final QuestionAnswerDTO capturedExpectedObject = qaRepositoryArgumentCaptor.getValue();

        assertThat(capturedExpectedObject.getAnswer()).isEqualTo(ANSWER);
        assertThat(capturedExpectedObject.getQuestion()).isEqualTo(QUESTION);
    }

    @Test
    void getQaList() {
        when(qaRepository.findAll()).thenReturn(List.of(expectedObject));

        final QuestionAnswerDTO questionAnswerDTO = qaService.getQaList().iterator().next();

        assertThat(questionAnswerDTO).isEqualTo(expectedObject);
    }
}
