package com.tywdi.backend.model.qaVO;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;

    public QuestionAnswerVO() {
        //hibernate dft ctor
    }

    public QuestionAnswerVO(final String answer, final String question) {
        this.question = question;
        this.answer = answer;
        this.date = LocalDateTime.now();
    }
}
