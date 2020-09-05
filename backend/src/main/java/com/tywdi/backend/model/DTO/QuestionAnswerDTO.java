package com.tywdi.backend.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.qaVO
 * Date: 05.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity(name = "QUESTION_ANSWER")
@Data
public final class QuestionAnswerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QA_SEQ")
    @SequenceGenerator(name = "QA_SEQ", sequenceName = "QA_SEQ", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Lob
    private String question;

    @NotBlank(message = "Name is mandatory")
    @Lob
    private String answer;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Version
    @JsonIgnore
    private int version;

    public QuestionAnswerDTO() {
        //hibernate dft ctor
    }

    public QuestionAnswerDTO(final String answer, final String question, final Category category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public enum Category {

        BASIC,
        ADVANCED,
        GODLIKE,
        NONE
    }
}
