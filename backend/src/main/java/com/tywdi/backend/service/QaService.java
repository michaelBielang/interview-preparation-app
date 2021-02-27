package com.tywdi.backend.service;


import com.tywdi.backend.exceptions.QuestionNotFoundException;
import com.tywdi.backend.model.dto.QuestionAnswerDTO;
import com.tywdi.backend.repository.QaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class QaService implements QaServiceInterface {

    @Autowired
    private QaRepository qaRepository;

    @Override
    @Transactional
    public QuestionAnswerDTO addQa(final String answer, final String question, final QuestionAnswerDTO.Category category) {
        final QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(answer, question, category);

        return qaRepository.save(questionAnswerDTO);
    }

    @Override
    public Iterable<QuestionAnswerDTO> getQaList() {
        return qaRepository.findAll();
    }

    @Override
    public QuestionAnswerDTO getQuestion(final String id) {
        return qaRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new QuestionNotFoundException("question-not-found", "Question " + id + "not found"));
    }

    @Override
    @Transactional
    public QuestionAnswerDTO updateQuestion(final QuestionAnswerDTO updatedQAV, final String id) {
        final QuestionAnswerDTO questionAnswerDTO = getQuestion(id);

        getQuestion(id).setAnswer(updatedQAV.getAnswer());
        getQuestion(id).setQuestion(updatedQAV.getQuestion());

        return questionAnswerDTO;
    }
}
