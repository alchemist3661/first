package com.forum.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Arthur on 18.2.16.
 */

public class TokenAuthenticationProvider implements AuthenticationProvider {
    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationProvider.class);
    @Autowired
    private final UserDetailsService userDetailsService;

    public TokenAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        log.debug("created TokenAuthenticationProvider");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("in authentication");
        if (authentication.getPrincipal() == null){
            authentication.setAuthenticated(true);
            return authentication;
        }
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        String token = (String) tokenAuthentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Unknown token");
        }
        tokenAuthentication.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        return authentication;
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication == TokenAuthentication.class;
    }
}