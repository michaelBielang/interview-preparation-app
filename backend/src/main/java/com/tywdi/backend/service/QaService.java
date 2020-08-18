package com.tywdi.backend.service;


import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import com.tywdi.backend.repository.QaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.service
 * Date: 05.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class QaService {

    @Autowired
    private QaRepository qaRepository;

    @Transactional
    public QuestionAnswerDTO addQa(final String answer, final String question, final QuestionAnswerDTO.Category category) {
        final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(answer, question, category);
        return qaRepository.save(questionAnswerDTO);
    }

    public Iterable<QuestionAnswerDTO> getQaList() {
        return qaRepository.findAll();
    }

    public Optional<QuestionAnswerDTO> getQuestion(final String id) {
        return Optional.ofNullable(qaRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not present")));
    }

    @Transactional
    public void updateQuestion(final QuestionAnswerDTO updatedQAV, final String id) {
        final QuestionAnswerDTO questionAnswerDTO = qaRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not present"));

        questionAnswerDTO.setAnswer(updatedQAV.getAnswer());
        questionAnswerDTO.setQuestion(updatedQAV.getQuestion());
    }
}
