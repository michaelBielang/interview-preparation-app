package com.tywdi.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.model
 * Date: 10.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity
@Data
public class User {

    @Id
    private String email;
    private String userName;
    private String password;
    private LocalDateTime localDateTime;

    public User() {
        //dflt ctor for hibernate
    }

    public User(final String userName, final String email, final String password) {
        this.userName = userName;
        this.password = password; // TODO: 10.08.2020 salt
        this.email = email;
        this.localDateTime = LocalDateTime.now();
    }
}
