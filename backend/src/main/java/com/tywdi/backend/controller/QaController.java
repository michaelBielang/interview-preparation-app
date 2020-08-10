package com.tywdi.backend.controller;


import com.tywdi.backend.model.qaVO.QuestionAnswerVO;
import com.tywdi.backend.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.controller
 * Date: 08.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@RestController
@RequestMapping(value = "/app")
public class QaController {

    @Autowired
    private QaService qaService;

    @GetMapping(value = "/questions")
    public Iterable<QuestionAnswerVO> getQuestions() {
        return qaService.getQaList();
    }

    @PostMapping(value = "/add-question")
    public void addNewQa(@RequestBody @Valid QuestionAnswerVO questionAnswerVO) {
        qaService.addQa(questionAnswerVO.getAnswer(), questionAnswerVO.getQuestion());
    }
}
