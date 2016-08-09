package com.forum.server.security;

import com.forum.server.dto.response.QueryErrorDto;
import com.forum.server.dto.response.QueryResultInfoErrorDto;
import com.forum.server.security.exceptions.TokenAuthenticationHeaderNotFound;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Arthur on 18.2.16.
 */
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("ORIGIN"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, " +
                "access-control-allow-origin, auth-token");
        response.setHeader("Content-Type", "application/json");
        log.info("In commence. Added headers");
        QueryResultInfoErrorDto infoDto;
        if (authException instanceof TokenAuthenticationHeaderNotFound) {
            response.setStatus(403);
            infoDto = new QueryResultInfoErrorDto("403", "fail", authException.getMessage());
        } else if (authException instanceof UsernameNotFoundException){
            response.setStatus(403);
            infoDto = new QueryResultInfoErrorDto("403", "fail", authException.getMessage());
        } else {
            response.setStatus(401);
            infoDto = new QueryResultInfoErrorDto("401", "fail", authException.getMessage());
        }
        ObjectMapper mapper = new ObjectMapper();
        response.getOutputStream().print(mapper.writeValueAsString(new QueryErrorDto(infoDto)));
        log.info("created ResponseErrorDto");
    }
}