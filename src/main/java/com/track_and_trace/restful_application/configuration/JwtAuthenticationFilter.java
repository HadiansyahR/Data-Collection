package com.track_and_trace.restful_application.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {

    private static final String TOKEN_HEADER_PREFIX = "Bearer ";

    private JwtTokenVerifier jwtTokenVerifier;

    public JwtAuthenticationFilter(JwtTokenVerifier jwtTokenVerifier){
        this.jwtTokenVerifier = jwtTokenVerifier;
    }

    private String getToken(HttpServletRequest request){
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_HEADER_PREFIX)){

//            System.out.println("Bearer token: "+bearerToken.substring(TOKEN_HEADER_PREFIX.length()));
            return bearerToken.substring(TOKEN_HEADER_PREFIX.length());
        }

        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = getToken(request);

        if (token != null){
            Authentication authentication = jwtTokenVerifier.isVerified(token);
            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
