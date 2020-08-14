package com.tywdi.backend.service;


import com.tywdi.backend.model.qaVO.QuestionAnswerVO;
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
    public void addQa(final String answer, final String question) {
        final QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO(answer, question);
        qaRepository.save(questionAnswerVO);
    }

    public Iterable<QuestionAnswerVO> getQaList() {
        return qaRepository.findAll();
    }

    public Optional<QuestionAnswerVO> getQuestion(final String id) {
        return Optional.ofNullable(qaRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not present")));
    }

    @Transactional
    public void updateQuestion(final QuestionAnswerVO updatedQAV, final String id) {
        final QuestionAnswerVO questionAnswerVO = qaRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not present"));

        questionAnswerVO.setAnswer(updatedQAV.getAnswer());
        questionAnswerVO.setQuestion(updatedQAV.getQuestion());
    }
}
