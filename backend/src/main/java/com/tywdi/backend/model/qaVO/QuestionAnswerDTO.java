package com.tywdi.backend.model.qaVO;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String question;

    @NotBlank(message = "Name is mandatory")
    private String answer;

    public QuestionAnswerDTO() {
        //hibernate dft ctor
    }

    public QuestionAnswerDTO(final String answer, final String question) {
        this.question = question;
        this.answer = answer;
    }
}
