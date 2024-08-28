package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.configuration.JwtTokenGenerator;
import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.request.LoginRequest;
import com.track_and_trace.restful_application.model.response.TokenResponse;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.service.implement.AccountServiceImpl;
import com.track_and_trace.restful_application.service.implement.CurrentUserDetailsServiceImpl;
import com.track_and_trace.restful_application.service.implement.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private CurrentUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private ValidationService validationService;

    @PostMapping("auth/token")
    public WebResponse<TokenResponse> generateToken(@RequestBody LoginRequest loginRequest) {

        validationService.validate(loginRequest);

        // Load user details using the username
        log.info("Auth Controller login request password: {}", loginRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        Account account = accountService.getAccountByUsername(userDetails.getUsername());

        if (accountService.checkAccountPassword(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getFlag())){
            TokenResponse tokenResponse = jwtTokenGenerator.generateToken(account);
            return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
        }

        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong username or password");

    }

}
