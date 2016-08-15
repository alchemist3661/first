package com.forum.server.security;

import com.forum.server.security.user.UserDetailsImpl;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

/**
 * Created by Arthur on 18.2.16.
 */
public class TokenAuthentication extends AbstractAuthenticationToken {
    private final String token;
    public TokenAuthentication(String token) {
        super(UserDetailsImpl.createDefaultAuthorities());
        this.token = token;
    }

    public TokenAuthentication(String token, Collection authorities) {
        super(authorities);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return this.getAuthorities();
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

}
