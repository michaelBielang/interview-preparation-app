package com.tywdi.backend.qaVO;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.qaVO
 * Date: 05.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity
@Data
public class QuestionAnswerVO {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String question;

    @NotBlank(message = "Name is mandatory")
    private String answer;

    private LocalDateTime date;

    public QuestionAnswerVO() {
        //hibernate dft ctor
    }

    public QuestionAnswerVO(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.date = LocalDateTime.now();
    }
}
