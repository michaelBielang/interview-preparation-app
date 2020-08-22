package com.tywdi.backend.repository;


import com.tywdi.backend.model.DTO.QuestionAnswerDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Organisation: Codemerger Ldt.
 * Project: qa
 * Package: com.twentytwenty.qa.repository
 * Date: 05.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Repository
public interface QaRepository extends CrudRepository<QuestionAnswerDTO, Long> {
}
