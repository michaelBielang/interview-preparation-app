package com.tywdi.backend.service;


import com.tywdi.backend.model.qaVO.QuestionAnswerVO;
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
}
