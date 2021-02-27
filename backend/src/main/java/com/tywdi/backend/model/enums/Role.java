package com.tywdi.backend.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.model.Enums
 * Date: 19.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public enum Role implements GrantedAuthority {

    GUEST("ROLE_GUEST"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class RoleMapping {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String GUEST = "ROLE_GUEST";
        public static final String USER = "ROLE_USER";
    }
}
