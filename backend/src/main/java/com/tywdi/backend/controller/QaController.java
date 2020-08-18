package com.tywdi.backend.controller;


import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import com.tywdi.backend.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
public final class QaController {

    @Autowired
    private QaService qaService;

    @GetMapping(value = "/questions")
    public Iterable<QuestionAnswerDTO> getQuestions() {
        return qaService.getQaList();
    }

    @GetMapping(value = "/question")
    public Optional<QuestionAnswerDTO> getQuestion(@RequestParam("id") final String id) {
        return qaService.getQuestion(id);
    }

    @PostMapping(value = "/question")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionAnswerDTO addNewQa(@RequestBody @Valid final QuestionAnswerDTO questionAnswerDTO) {
        return qaService.addQa(questionAnswerDTO.getAnswer(), questionAnswerDTO.getQuestion(), questionAnswerDTO.getCategory());
    }

    @PutMapping(value = "question/{id}")
    public void updateQuestion(@RequestBody @Valid final QuestionAnswerDTO updatedQAV, @PathVariable final String id) {
        qaService.updateQuestion(updatedQAV, id);
    }
}
