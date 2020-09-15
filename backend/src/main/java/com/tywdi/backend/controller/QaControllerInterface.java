package com.tywdi.backend.controller;

import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface QaControllerInterface {
    @GetMapping(value = "/questions")
    Iterable<QuestionAnswerDTO> getQuestions();

    @GetMapping(value = "/question")
    QuestionAnswerDTO getQuestion(@RequestParam("id") String id);

    @PostMapping(value = "/question")
    @ResponseStatus(HttpStatus.CREATED)
    QuestionAnswerDTO addNewQa(@RequestBody @Valid QuestionAnswerDTO questionAnswerDTO);

    QuestionAnswerDTO updateQuestion(@RequestBody @Valid QuestionAnswerDTO updatedQAV, @PathVariable String id);
}
