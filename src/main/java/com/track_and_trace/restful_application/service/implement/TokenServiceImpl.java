package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.configuration.JwtTokenGenerator;
import com.track_and_trace.restful_application.entity.CurrentPrincipal;
import com.track_and_trace.restful_application.model.response.TokenResponse;
import com.track_and_trace.restful_application.model.request.UsernamePasswordRequest;
import com.track_and_trace.restful_application.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenGenerator tokenGenerator;

    @Override
    public TokenResponse createToken(UsernamePasswordRequest usernamePasswordRequest) {
        UsernamePasswordAuthenticationToken uPass = new UsernamePasswordAuthenticationToken(usernamePasswordRequest.getUsername(), usernamePasswordRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(uPass);
        CurrentPrincipal principal = (CurrentPrincipal) auth.getPrincipal();

        return tokenGenerator.generateToken(principal.getAccount());
    }
}
