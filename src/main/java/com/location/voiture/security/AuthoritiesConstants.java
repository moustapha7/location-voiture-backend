package com.location.voiture.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String GERANT = "ROLE_GERANT";

    public static final String VISITEUR = "ROLE_VISITEUR";

    public static final String CLIENT = "ROLE_CLIENT";

    private AuthoritiesConstants() {
    }
}
