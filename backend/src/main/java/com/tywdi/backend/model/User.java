package com.tywdi.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tywdi.backend.model.Enums.Role;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String email;
    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        //dflt ctor for hibernate
    }

    public User(final String username, @Email final String email, final String password, final Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
